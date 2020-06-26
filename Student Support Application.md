# CSC8002 Assessed CourseworkStudent Support Application

## 1.Aim

The aim of this coursework is for you to practice the design principles covered in lectures. You will develop interfaces and classes to demonstrate that you have learned and understood the module material, including:

* appropriate overriding of Object class methods, including overriding toString

and providing a static valueOfmethod when appropriate

* design of interface based hierarchies, programming through interfaces and late 

binding

* the use of factories to control instantiation of objects, including how to guarantee the instantiation of unique instances

* defensive programming including the use of immutability

* the use of appropriate interfaces and classes from the Collections framework The coursework is not algorithmically challenging. The focus is on good design and good practice.

The coursework is not about development of an end user application. You are developing interfaces and classes that could be used for the development of an application. You should not develop a graphical user interface or a command line interface. They are not necessary and you will be given no credit for doing so. You should provide test cases for your interfaces and classes. 

You should submit both your solution and the classes that you use to test the solution.

Note

the student support system specified below is a deliberate simplification. It is not an 

accurate  model  of  real  world University  systems.  Your  solution  should  correspond  to  the simplicity of the specification. You risk losing marks if you attempt to provide a more realistic model or provide a solution that is more complicated than necessary.



## 2. System overview

A University Department needs a set of interfaces and classes to manage

student data. The Department has different types of students.  These are 

undergraduate(UG), postgraduate taught (PGT) and postgraduate research (PGR) students. Students cannot be more than one type. For this coursework, the significant difference between undergraduate and postgraduate taught  students is  that undergraduate  students  take  120  credits  worth  of  courses  in  a  year whereas postgraduate taught students take 180. Furthermore, the pass mark for undergraduate modules is 40% but for postgraduate taught modules is 50%.Postgraduate research students have a supervisor but do not register for modules.The University needs  to  maintain  a  record  of modules and  supervisors  for  students in an academic year. The system should allow module and supervisor information to be read from appropriately defined data files. The files should contain one data entry per line with fields separated by a comma e.g.CSC8002, Advanced Programming, 20.

The system should allow an appropriate number of modules to be added to a student record and to record whether or not a student is correctly registered (are they taking the right number of credits or do they have a supervisor allocated).In addition, the University needs to be able to issue smart cardsand login ID’s to all students on their courses. The following provides more detail on the required functionality:

noOfStudents(typeOfStudent)

This method returns the number of students of the specified type that are currently enrolled.

registerStudent(Student)

This method registers a new student onto the system and allocates a student ID (see 

below). amendStudentData(studentID, studentData)This method changes a student record. 

terminate Student(studentID)

This method removes the student record associated with the given student number

. In effect, the student is leaving theUniversity. 



When issuing a smart card, the following rules must be observed.An undergraduate student must be at least 17 years old. 

A postgraduate student must be at least 20 years old.

A student cannot be issued with more than one smartcard(i.e. do not try to deal with 

lost cards!).



## 3. Implementation

To complete the system outlined in Section 2 you will need to provide interfaces and classes for the functionality described in this section. You must also use Junit testing to unit test your solution. Student All students have the following public functionality:

* a method to get the student’s ID.
* a method to get the student’s type (UG, PGT, or PGR).
* a method to list the modules that the student is registered for. A module consists of a name (e.g. Programming 2), a module code (e.g. CSC1022) and the number of credits associated with the module (e.g. 20).

* A method which returns true if the student is currently registered for enough credits 

(120 for undergraduate, 180 for postgraduate taught, 0 for postgraduate research) and 

false otherwise.

* Postgraduate research students have a method to return the name of their supervisor (a Name) You must provide an appropriate hierarchy for students. 

### Student ID

A student ID has two components 

a single letter followed by a four digit number. For example:

* a1234

You must provide access to each component and an appropriate string representation of the ID. Student ID’s are unique. You must guarantee that no two students have the same ID

### Smart Card

A Smart Card has the student's name (comprising a first and last name), the date of birth of the student, a unique Smart Card number and a date of issue. The Smart Card number has three components. The first component is the concatenation of the initial of the first name of the student with the initial of the last name of the student. The second component is the year of issue of the card. The third component is an arbitrary serial number. For example, the string representation of the Smart Card number for a card issued to John Smith in 2018 would have the form:

* JS-2018-10
* 

where the 10 is a serial number that, with the initials and year, guarantees the uniqueness of the card number as a whole. Your smart card class must provide methods to access the student's name, the student's date of birth, the student ID and the date of issue of the Card.

You should provide appropriate classes for a student's name and for a smart card number.

You must guarantee the uniqueness of smart card numbers.

You should use the java.util.Date class to represent dates. However, you must not use 

deprecated  methods  of  the Dateclass.  So,  for  example,  in  your  test  classes  use 

java.util.Calendar to construct dates of birth and dates of issue ofSmart Cards

 You can assume default time zone and locale.

Note:

Java 8 provides a much more satisfactory way of handling dates and time

with immutable classes. However, in this coursework I want to see that you can use sub

optimal classes such as Date and so its use in this project is mandatory.

The smart card should have the following private method:

* setExpiryDate(); which sets an expiry date for the card. If the smart card is held by a 

UG student, the expiry date is set to the issue date plus four years. If the smart card is 

held by a PGT student, the expiry date is set to the issue date plus two years. If the smart card is held by a PGR student, the expiry date is set to the issue date plus five years.

The smart card should have the following public method:

* getExpiryDate(); which returns the expiry date of the card.