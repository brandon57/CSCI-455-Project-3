# CSCI-455-Project-3


# These are the requirements for the project
<p align="center">
  <img src="Documents/project3_2023_Fall_pg1.png" width="688" />
</p>

<p align="center">
  <img src="Documents/project3_2023_Fall_pg2.png" width="688" />
</p>

<p align="center">
  <img src="Documents/project3_2023_Fall_pg3.png" width="688" />
</p>

# Getting Started

In order to run this you have to have a Hadoop server running.
In this case I'll be using the Hadoop server that NDSU has called zoidberg.cs.ndsu.nodak.edu.

## Uploading files
Once you have that you'll need to upload the java files and the input file to the server. To do that you can use either [FileZilla](https://filezilla-project.org/) or [WinSCP](https://winscp.net/eng/index.php) to connect to the server through SFTP

## Setup
Now that all the files are transfered over we'll need to do a few things before we can run the program.

### Connecting
First you'll need to connect to the server through SSH. To do this you can either use a software call [PuTTY](https://www.putty.org/)
or you can use the terminal, command prompt or powershell to connect to the server.

Note: Throughout this I'll be using the command prompt in windows but everything should be the same

### Inputs
Now that you are connected we'll need to transfer the input file to the HDFS (Hadoop Distributed File System).
To do this we'll need to make some directories.

* First we'll create a directory that we'll place everything in. Using this command will do just that:[^1]
  ```
    hadoop fs -mkdir /user/USERNAME_HERE/project-3-455
  ```
* Next we'll create the directory where your input file will go:[^1]
  ```
    hadoop fs -mkdir /user/USERNAME_HERE/project-3-455/input
  ```
[^1]: Seeing the word USERNAME_HERE means you put in your own username

* Now that we created the directory for the input file we can now copy that over. Running this command will copy it over:[^1][^2]
  ```
    hadoop fs -copyFromLocal /home/USERNAME_HERE/FILE_NAME_HERE /user/USERNAME_HERE/project-3-455/input/FILE_NAME_HERE
  ```
[^2]: Seeing the word FILE_NAME_HERE means you put in the name of the file you want to copy over

### Compiling java files
Now that you've copied over the input file we now need to compile the java files so that we can actually run the program.

* To do that we'll first need the class files from each java file:
  ```
    hadoop com.sun.tools.javac.Main Hadoop.java DataMapper.java DataReducer.java Table.java
  ```

* Next we'll create a jar file with the class files we just created:
  ```
    jar cf Hadoop.jar Hadoop*.class DataMapper*.class DataReducer*.class Table*.class
  ```

# Running The Program
Now that we have the jar file we can now run the program.

To run it we'll use this command:[^1][^2]
```
hadoop jar Hadoop.jar Hadoop /user/USERNAME_HERE/project-3-455/input /user/USERNAME_HERE/project-3-455/output
```
After running this command the program will run.

Once the program is done running there will be 4 files inside the output directory that the program produced.
To see what each file reads you need to see what each file's name is

Running this command will list out all the files inside the directory:[^1]
```
hadoop fs -ls /user/USERNAME_HERE/project-3-455/output
```
Should look something like this:
```
brandon.sitarz@zoidberg:~$ hadoop fs -ls /user/brandon.sitarz/Project-3-455/output
Found 5 items
-rw-r--r--   3 brandon.sitarz nogroup          0 2023-12-03 20:31 /user/brandon.sitarz/Project-3-455/output/_SUCCESS
-rw-r--r--   3 brandon.sitarz nogroup     463416 2023-12-03 20:31 /user/brandon.sitarz/Project-3-455/output/part-r-00000
-rw-r--r--   3 brandon.sitarz nogroup     401444 2023-12-03 20:31 /user/brandon.sitarz/Project-3-455/output/part-r-00001
-rw-r--r--   3 brandon.sitarz nogroup     682883 2023-12-03 20:31 /user/brandon.sitarz/Project-3-455/output/part-r-00002
-rw-r--r--   3 brandon.sitarz nogroup     543381 2023-12-03 20:31 /user/brandon.sitarz/Project-3-455/output/part-r-00003
```
Now to read the files you can run this:[^1]
```
hadoop fs -cat /user/USERNAME_HERE/project-3-455/output/part-r-*
```
```

```
