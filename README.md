Test Task
=========

Prerequisites
-------------

* java 8
* maven 3

Build and Run
-------------

1. Run in the command line:
	```
	mvn package
	mvn jetty:run
	```

2. Open `http://localhost:8080` in a web browser.
3. UI:
	On page "Banks" create banks, choose a bank.
	On page "Bank" create credits, add client if have created clients.
	On page "Clients" create clients, choose a client and add banks.
	On "Client" page choose bank and choose available credit, create credit offer.
