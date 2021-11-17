# throttling-library
throttling-library

# Purpose
Implement a simple throttling implementation which works seamlessly with spring-boot rest framework.

# Approach
The idea is to use interceptors concept of spring to intercept any calls that are coming in 
and ensure that if the volume of calls go above a given limit the request is delayed. 

The solution will gracefully increase and decrease the delay based on the volume of traffic. 

throttling_limit -> TPS above which throttling will be applied

throttling_delay -> The delay in millis if throttling is enabled

We are leveraging spring scheduler to measure the number of hits received in last second to determine TPS.

If the TPS exceeds throttling_limit then throttling logic will get activated.

# Solution
We will leverage the concept of Interceptors in spring boot which will be triggered before the controller is activated.

# TODO 
This is a plain throttler. The following can be implemented later:

- Throttling based on Client / IP
- Throttling based on API key
- HTTP 503 support
