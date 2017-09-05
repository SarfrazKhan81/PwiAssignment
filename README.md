# PwiAssignment

This Project was an assignment to build a solution to a senario provided in a PDF file. Please contact at sarfraz.khan(@)gmail.com for any questions.

The solution was supposed to implement 5 following points
1. Add/Edit/Delete Item (Product, Component, Packaging Material)

Add:
URL= http://localhost:8080/PwiAssignment/addProdServ/
Method= POST
ContentType= application/json
Payload = {"brandName":"Brand2","productName":"ProductF","productType":"Finished","sizes":["110 ml","120 ml","130 ml"]}

VIEW:
http://localhost:8080/PwiAssignment/prodServ/4
Method= GET
SAMPLE OUTPUT= 
{
"pid": 4,
"brandName": "Brand2",
"productName": "ProductB",
"productType": "Finished",
"sizes": [
  "110 ml",
  "120 ml",
  "130 ml"
]
}



EDIT:
URL= http://localhost:8080/PwiAssignment/editProdServ/
Method= PUT
ContentType= application/json
Payload= {"pid","4","brandName":"Brand2","productName":"ProductB","productType":"Finished","sizes":["110 ml","120 ml","130 ml"]}


DELETE:
URL= http://localhost:8080/PwiAssignment/delProdServ/5
Method= Delete


2. Add/Edit/Delete Warehouse
Add:
URL= http://localhost:8080/PwiAssignment/addwarehouseServ/
Method= POST
ContentType= application/json
Payload = {"warehouseName":"Warehouse6","locationName":"Netherlands","warehouseType":"W"}


VIEW:
http://localhost:8080/PwiAssignment/warehouseServ/1
Method= GET
SAMPLE OUTPUT= 
{
"wid": 1,
"locationName": "USA",
"warehouseName": "Warehouse1",
"warehouseType": "W",
"inventories": null
}

EDIT:
URL= http://localhost:8080/PwiAssignment/editwarehouseServ/
Method= POST
Content-Type: application/json
Payload= {"wid":"15","warehouseName":"Warehouse6","locationName":"Netherlands","warehouseType":"W"}

DELETE:
URL= http://localhost:8080/PwiAssignment/delWarehouseServ/14
Method= Delete


3. Set Item Quantity in Single/All Warehouse(s) of Company or any Office(s).

SET ITEM (SINGLE Warehouse or Office)
We suppose that the front end will send ID that could be of any warehouse or office (ID can be seen from view warehouse API with type 'W' for warehouse and 'O' for Office) along with productID and ProductSizeID.

URL= http://localhost:8080/PwiAssignment/setInventoryServ/
Method= POST
ContentType= application/json
Payload = {"pid":"1","psid":"1","wid":"11","instock":"1","avlqty":"1111","intransit":"1","moq":"1","qpb":"1","rop":"1"}


SET ITEM (ALL Warehouse or Office)
We suppose that the front end will send productID and ProductSizeID (Logically it seems that this API will not be used).

URL= http://localhost:8080/PwiAssignment/setAllInventoryServ/
Method= POST
ContentType= application/json
Payload = {"pid":"1","psid":"1","instock":"1","avlqty":"1111","intransit":"1","moq":"1","qpb":"1","rop":"1"}


4. View Item Quantity in Single/All Warehouse(s) of Company or any Office(s).
VIEW ITEM (SINGLE Warehouse or Office)
We suppose that the front end will send ID that could be of any warehouse or office (ID can be seen from view warehouse API with type 'W' for warehouse and 'O' for Office) along with productID and ProductSizeID.

URL= http://localhost:8080/PwiAssignment/getInventoryServ/
Method= POST
ContentType= application/json
Payload = {"pid":"1","psid":"1","wid":"11"}


VIEW ITEMS (ALL Warehouse or Office)
We suppose that the front end will send productID and ProductSizeID.

URL= http://localhost:8080/PwiAssignment/setAllInventoryServ/
Method= POST
ContentType= application/json
Payload = {"pid":"1","psid":"1"}



5. See all available sizes of any Item

If you want product sizes against any product id, we can use the VIEW API of the first section, but incase we need Product sizes against Product Name than we can use this below mentioned API

http://localhost:8080/PwiAssignment/prodSizes/ProductB
Method= GET
SAMPLE OUTPUT= 
{
"pid": 4,
"brandName": "Brand2",
"productName": "ProductB",
"productType": "Finished",
"sizes": [
  "110 ml",
  "120 ml",
  "130 ml"
]
}
