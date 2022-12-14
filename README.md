# Assignment 02 - 4DV507 – Code Transformation and Interpretation

Authors:
- Nico Pistor       - np222kk
- Christoph Zwick   - cz222de

Supervisor:
- Jonas Lundberg


## Run the application
Several launch configurations has been defined in .vscode\launch.json
These configurations are independent of individual paths. It is just necessary to open the workspace in VS Code.

### TODOs
- Withing the .code-workspace file it is specified where the ANTLR library is located. This path may have to be changed in order to run this application.
- The following VS Code extension pack has been used to compile and run the Java application inside VS Code: https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack 
- Unfortunately VS Code adds a suffix to the project name: "projectName": "np222kk.dv507.A2_e698d8bf". Therefore, the launch configurations will not work out of the box. The App has to be started manually through clicking on the Run button that is generated by VS Code over the main function in the App.java class. This execution will run into an error as there is no input parameter given. But a new entry has been made by VS Code within the launch.json file. Now the generated project name can be copied into the other launch configurations.


### Launch configurations:
- Launch App: Start the application without an input parameter
- Debug current OFP file: Start the application with the currently openend ofp file as input parameter
- Launch Language Test: This is an alternative entrypoint into the application. All testscases will be executed and the result will be printed into the console as a list.
- ANTLR current grammar: Generate Parse Tree with the currently selected grammar file
- ANTLR current ofp file: Generate Parse Tree for the currently opened ofp file


# Test the Application
np222kk.dv507.a2.test.LanguageTest.java
This is an alternative entrypoint into the application. All testcases will be executed. The testcases are specified trough the input parameters which are defined within the launch configuration.

It is possible that testcases in a specific folder are supposed to fail to enable testing of failing scenarios. These cases will be showed as a success if an error has been detected during execution.


# The code
The source code has been structurized into several packages.

- np222kk.dv507.a2.language: Contains the .g4 file the the ANTLR generated files
- np222kk.dv507.a2.semanticalanalysis: Contains the semantial analysis. SemanticalAnalysis.java contains the logic to coordinate the different steps of the semantical analysis
- np222kk.dv507.a2.syntaxanalysis: Contains the lexical analysis and generation of the parse tree
- np222kk.dv507.a2.test: contains all test cases, as well as classes just for testing

np222kk.dv507.a2.App.java is the entry point to the application. It is possible to specify an input ofp file.
