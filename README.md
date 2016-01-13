## Synopsis

MySQL data scrubbing tool. It allows to define rules that specify how to scrub data on per column basis. 

### REST API

POST /
GET /sets/

Well what should it do? 

You should be able to submit a rule set, a database, and ask it to run. You could optionally supply a username and password to the database. In response you should be able to get a response code and a number that it's running at. It should automatically connect to vault and ask it for a password, connect to Consul and get the mysqlserver. If there are multiple potential instances, it'll return all of them and then you'd have to specify in you request which one do you want these rules to be acted upon.

Currently it's a tree. You start running all the high-level rules. 

It should be just a fucking list.

* What if there are rules that act on the same table in the first level?

It keeps a list of tables that datascrubber is working on.

It's just a list. It gets the list, and then tries to act on them. 

So a list of tables. You just specify which ones you want, and it tries to run them. If there's a rule in list of the currently running tables, it will wait and keep itariting over them.

a 'run' consists of a list of rules to run, list of rules running.

You should be able to get a status code on a running set.

### Types of rules 
* Shuffle
* Substitution
  * Fixed string substitution
  * From file
  * Date substitution
  * and other(?)
  
### Testing
* Create a database called dstesting
* load create_data.sql in it.
