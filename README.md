# tacho

This is an back-end app to organise work between agencies and HGV drivers who run their own limited companies.

Work in progress.

Api documentation: Swagger,

About this app:

Agency can create "Duty" which can be accepted by a driver (Assignment) and then he/she can insert entries into it to calculate an entire shift time and check if drivers' hours rules has been violated (Infringement).

Furthermore, Duty can be updated by adding an extra allowance: 
- if driver had to take night out into the cab (The Decorator pattern is implemented for that) 
- if duty falls on a bank holiday. For this occasion it's updated automatically (external API "Calendarific" is used here) 

Also, Infringements are marked as valid only for 28 days as drivers' hours rules states and after that time all not valid records are automatically removed from the database by Scheduler.




