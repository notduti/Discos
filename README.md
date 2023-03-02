# Discos

Java swing application that uses SQLite db.

Realised by Federico Dutto 4°A INF 2022/23 - ITIS M. Delpozzo Cuneo
You can redistribute this code and/or modify it.


---- Use cases: ----

On the main page there is a list of all the owners of the discos, sorted by 5 parametres:
- id from the
- name
- surname
- residence
- date of birth

Here the user can either add, or delete or update an owners.
If he decides either to update an existing owner or to add a new one, another page will be shown.

On this page all the previous fields can be modified, and there is also a list of discos for each owner.
A disco has the following parametres:
- id of the disco
- id of the owner (external key of db)
- name 
- capacity

For each disco, the user can either add, or delete or update.
If he decides either to update or to add, another dialog to update all the fields will appear.

