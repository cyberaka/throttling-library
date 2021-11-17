# throttling-library
throttling-library

# Purpose
Implement a simple thorttling implementation which works seamlessly with spring-boot rest framework.

# Approach
The idea is to use interceptors concept of spring to intercept any calls that are coming in and ensure that if the volume of calls go above a given limit the request is exponentially delayed. 

The solution will gracefully increase and decrease the delay based on the volume of traffic. 

throttling_limit -> Decides after how many concurrent connections does throttling kick in

throttling_delay -> The delay factor to be introduced in subsequent calls

throttling_increment -> The increment for throttling delay if connections don't go down. 

We basically count the number of connections that came in and got concluded in a period of time.

if the number of concurrent connections which are not concluded exceeds throttling_limit then throttling logic will get activated.

# Solution
We will leverage the concept of Interceptors in spring boot which will be triggered before the controller is activated.

# Useful Articles
https://www.tutorialspoint.com/spring_boot/spring_boot_interceptor.htm
