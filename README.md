# Booting up

To compile:
`mvn clean package install`

To Bootup:
`mvn spring-boot:run`

To Load test:
`jmeter.sh -n -t throttling-library/docs/Load_Test.jmx`

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

# Sample Execution

## JMeter Output
```
Starting standalone test @ Thu Nov 18 01:20:21 IST 2021 (1637178621706)
Waiting for possible Shutdown/StopTestNow/HeapDump/ThreadDump message on port 4445
summary +   1291 in 00:00:08 =  156.4/s Avg:   426 Min:     0 Max:   508 Err:     0 (0.00%) Active: 99 Started: 100 Finished: 1
summary +   5863 in 00:00:30 =  195.4/s Avg:   504 Min:   500 Max:   508 Err:     0 (0.00%) Active: 96 Started: 100 Finished: 4
summary =   7154 in 00:00:38 =  187.0/s Avg:   490 Min:     0 Max:   508 Err:     0 (0.00%)
summary +   2846 in 00:00:17 =  171.9/s Avg:   501 Min:     0 Max:   507 Err:     0 (0.00%) Active: 0 Started: 100 Finished: 100
summary =  10000 in 00:00:55 =  182.4/s Avg:   493 Min:     0 Max:   508 Err:     0 (0.00%)
Tidying up ...    @ Thu Nov 18 01:21:16 IST 2021 (1637178676571)
... end of run
```

## Throttler Output
```
01:18:11.069 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=0, delay=0}
01:18:12.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=112, delay=500}
01:18:13.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=52, delay=500}
01:18:14.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=190, delay=500}
01:18:15.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=132, delay=500}
01:18:16.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=169, delay=500}
01:18:17.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=198, delay=500}
01:18:18.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=2, connectionsPerSecond=198, delay=500}
01:18:19.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=198, delay=500}
01:18:20.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=198, delay=500}
01:18:21.069 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=200, delay=500}
01:18:22.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=197, delay=500}
01:18:23.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=198, delay=500}
01:18:24.069 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=198, delay=500}
01:18:25.069 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=1, connectionsPerSecond=197, delay=500}
01:18:26.069 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=199, delay=500}
01:18:27.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=200, delay=500}
01:18:28.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=196, delay=500}
01:18:29.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=200, delay=500}
01:18:30.069 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=1, connectionsPerSecond=197, delay=500}
01:18:31.069 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=4, connectionsPerSecond=199, delay=500}
01:18:32.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=196, delay=500}
01:18:33.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=2, connectionsPerSecond=200, delay=500}
01:18:34.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=196, delay=500}
01:18:35.069 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=200, delay=500}
01:18:36.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=197, delay=500}
01:18:37.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=197, delay=500}
01:18:38.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=200, delay=500}
01:18:39.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=200, delay=500}
01:18:40.069 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=197, delay=500}
01:18:41.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=195, delay=500}
01:18:42.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=1, connectionsPerSecond=199, delay=500}
01:18:43.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=195, delay=500}
01:18:44.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=196, delay=500}
01:18:45.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=196, delay=500}
01:18:46.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=196, delay=500}
01:18:47.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=3, connectionsPerSecond=191, delay=500}
01:18:48.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=192, delay=500}
01:18:49.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=190, delay=500}
01:18:50.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=188, delay=500}
01:18:51.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=182, delay=500}
01:18:52.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=188, delay=500}
01:18:53.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=186, delay=500}
01:18:54.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=188, delay=500}
01:18:55.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=188, delay=500}
01:18:56.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=2, connectionsPerSecond=188, delay=500}
01:18:57.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=183, delay=500}
01:18:58.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=186, delay=500}
01:18:59.069 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=186, delay=500}
01:19:00.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=181, delay=500}
01:19:01.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=185, delay=500}
01:19:02.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=3, connectionsPerSecond=186, delay=500}
01:19:03.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=157, delay=500}
01:19:04.072 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=125, delay=500}
01:19:05.070 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=86, delay=500}
01:19:06.071 [scheduling-1] INFO  c.c.p.t.logic.ThrottlerSchedule - Throttling Status = Throttler{connectionCount=0, connectionsPerSecond=54, delay=0}

```