G00349242 - Tomas Brazas
OOP Assignment

This Project is a Java API that rapidly compares two text files.
It computes their cosine distance outputting the percentage to the user.

Features:
*The application is ran by the Runner class that has a main method and executes the Menu.
*In the menu, user is greeted by a simple UI that asks for user to enter file names of the files
that the user wants to compare.
* Once entered the files are processed using the Processor class. This is a class that uses the
FileParser class to create objects and pass them into Threads which are created
for each document, also joins them for simulatenous use. CosineDistance object is also
produced here and calculated.
*Threads instantiate a ShingleTaker class object on the thread. This is where the Shingles
are used to calculate the cosinedistance between the entered documents of the user, Poison class assists this.
Shingles are processed to build maps.
*The CosineDistance class contains information needed to calculate CosineDistance DotProduct.
*The Shingle class contains information about documents.

