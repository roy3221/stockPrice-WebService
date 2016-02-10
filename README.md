# stockPrice-WebService
build up a connection to a realtime stock price web service.

##Preliminary
+ Having an account to Amazon Web Service
+ Having an SSH accessible EC2 instance
+ Having eclipse J2EE tools installed or Axis2 tool set installed

##Description
Create a program that is able to talk with a real time stock price web service. It should fetch latest price of all of companies in Dow Jones Industrial Average every 10 minutes (which is adjustable). The prices are written as key value pairs <stock symbol, price> into a txt file. In the end, the program will print out the maximum fluctuated stock symbol (with maximum standard deviation in last twenty four hours) into the that output file. The program can be able to run in the created EC2 instance.
