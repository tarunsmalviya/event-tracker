# event-tracker

This is a SCREEN ON, SCREEN OFF and UNLOCK event tracker android application.

### Architecture Details
* *EventListenerService* is a service which runs STICKY in the background, listening for the event occurrence and logs it. 

* *EventLogModel* is event log model class.

* *LogAdapter* is recycle list adapter.

* *MainActivity* is the activity in which event log is listed.

### Dependency Details
* [Realm](https://realm.io/) library is used to save the event logs.

* Android support libray.
