package gov.nist.toolkit.simulators.proxy.transforms

import ca.uhn.fhir.context.FhirContext
import gov.nist.toolkit.installation.ResourceCache
import gov.nist.toolkit.simulators.fhir.OperationOutcomeGenerator
import gov.nist.toolkit.simulators.fhir.WrapResourceInHttpResponse
import gov.nist.toolkit.simulators.proxy.exceptions.SimProxyTransformException
import gov.nist.toolkit.simulators.proxy.util.ContentResponseTransform
import gov.nist.toolkit.simulators.proxy.util.ResponsePartParser
import gov.nist.toolkit.simulators.proxy.util.SimProxyBase
import gov.nist.toolkit.soap.http.SoapFault
import gov.nist.toolkit.utilities.io.Io
import gov.nist.toolkit.xdsexception.ExceptionUtil
import org.apache.http.Header
import org.apache.http.HttpResponse
import org.apache.http.message.BasicHttpResponse
import org.apache.log4j.Logger
import org.hl7.fhir.dstu3.model.CodeableConcept
import org.hl7.fhir.dstu3.model.OperationOutcome
import org.hl7.fhir.dstu3.model.StringType
/**
 *
 */
class RegistryResponseToOperationOutcomeTransform implements ContentResponseTransform {
    static private final Logger logger = Logger.getLogger(RegistryResponseToOperationOutcomeTransform.class);

    @Override
    HttpResponse run(SimProxyBase base, HttpResponse response) {
        throw new SimProxyTransformException('run(SimProxyBase base, HttpResponse response) not implemented.')
    }

    @Override
    HttpResponse run(SimProxyBase base, BasicHttpResponse response) {
        FhirContext ctx = ResourceCache.ctx

        try {
            logger.info('Running RegistryResponseToOperationOutcomeTransform')
            String xmlBody
            Header contentTypeHeader = response.getHeaders('Content-Type')[0]
            if (contentTypeHeader.value.startsWith('multipart')) {
                String partContent = Io.getStringFromInputStream(response.getEntity().content)
                BasicHttpResponse part = ResponsePartParser.parse(partContent)
                xmlBody = Io.getStringFromInputStream(part.getEntity().content)
            } else
                throw new SimProxyTransformException('Not Implemented')
            def root = new XmlSlurper().parseText(xmlBody)
            def rootName = root.name()
            if (rootName == 'Envelope') {
                def fault = root?.Body?.Fault
                if (fault.text()) {
                    String code = fault.Code?.Value?.text()
                    println "Fault code is ${code}"
                    String reason = fault.Reason?.Text?.text()
                    println "Reason is ${reason}"
                    SoapFault soapFault = new SoapFault(code, reason)
                    OperationOutcome operationOutcome = OperationOutcomeGenerator.translate(soapFault)
                    return WrapResourceInHttpResponse.wrap(base, operationOutcome)
                }
                def body = root?.Body
                def bodyName = body?.name()
                def registryResponse = body?.RegistryResponse
                def registryResponseName = registryResponse.name()
                if (root?.Body?.RegistryResponse.name()) {
//                if (registryResponse.name()) {
                    String status = registryResponse.@status
                    if (status.endsWith('Failure')) {
                        OperationOutcome oo = new OperationOutcome()
                        registryResponse.RegistryErrorList?.children().each { registryError ->
                            String context = registryError.@codeContext
                            String errorCode = registryError.@errorCode
                            String location = registryError.@location
                            String severity = registryError.@severity
                            OperationOutcome.OperationOutcomeIssueComponent com = new OperationOutcome.OperationOutcomeIssueComponent()
                            com.setSeverity(OperationOutcome.IssueSeverity.ERROR)
                            com.setDiagnostics(context)
                            com.setLocation([new StringType(location)])
                            CodeableConcept cc = new CodeableConcept()
                            cc.setTextElement(new StringType(errorCode))
                            com.setDetails(cc)
                            oo.addIssue(com)
                        }
                        return WrapResourceInHttpResponse.wrap(base, oo)
                    }
                }
            }
            throw new SimProxyTransformException('RegistryResponseToOperationOutcomeTransform: Not Implemented')
        } catch (Exception e) {
            OperationOutcome oo = new OperationOutcome()
            OperationOutcome.OperationOutcomeIssueComponent com = new OperationOutcome.OperationOutcomeIssueComponent()
            com.setSeverity(OperationOutcome.IssueSeverity.FATAL)
            com.setCode(OperationOutcome.IssueType.EXCEPTION)
            com.setDiagnostics(ExceptionUtil.exception_details(e))
            oo.addIssue(com)
            return WrapResourceInHttpResponse.wrap(base, oo)
        }
    }
}