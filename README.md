Service-Client Exercise
=====

This project contains all the classes and tests to prove the concept of a 
server / client configuration as discussed on Thursday, October 31st.
Details of the problem are as follows:

Create a scheduler that takes in two types of jobs from any number of clients.
The scheduler should only execute one job at a time. Details in the email 
ask to keep the implementation simple. I may also try to tackle other details 
discussed during the initial conversation:

1. Scheduler should be able to prioritize jobs based on which client has been 
   least recently served.
2. Jobs should have a clean, simple interface to support multiple implementations.
3. Scheduler should only execute jobs one at a time.
4. All jobs queued by a particular client should be returned in the order in 
   which they were scheduled.

