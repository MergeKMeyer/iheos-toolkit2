<h3>Validate Cross Gateway Retrieve Image Document Set (RAD-69) Response from 
RIG SUT to IIG simulator.</h3>

<p/>When the test section is run, the testkit will evaluate the RAD-75 Response
sent by the RIG SUT during the retrieve section of the test, comparing it
with a 'gold standard' response in the testplan. Validations include:
<ol>
<li/>That the response parses successfully. 
<li/>Correct RegistryResponse status attribute value.
<li/>That no documents were returned.
<li/>That a RegistryErrorList element containing the appropriate RegistryError
element and attributes is present. &#42;
</ol>

&#42; A RegistryError is considered to be correct if it's errorCode and severity
attribute values match those in the 'gold standard' message.