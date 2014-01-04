HASH
====

Simple hashing function in Java
=======
      How to use:
Compile.bat will compile a java program and then run the program with multiple input files and then compare them to expected output. All lines in the output that are not exactly the same as the example output will be output into a file in the folder /Error.

Input files will be txt files with input structured exactly in the following manner. There is no input verification: all input and example output files are expected to be perfect. This input output schema just proves that the Hashing functions work and these hashing functions could be used for any I/O system given proper context.

>N - Print my name
                                   
>L sz - create a hash table of size sz that uses linear probing
                                
>Q sz - create a hash table of size sz that uses quadratic probing
                                
>D sz - create a hash table of size sz that uses double hashing with                                                    

>C - clear the hash table to empty and reset the statistics
                                
>A Keeps you clean:soap - Insert record "Keeps you clean" with "soap" as its key.

>>    Print one of the lines "Key soap inserted", "Key soap already exists", 

>> OR

>>    Table has overflowed". In the last case, the record isn't inserted.
                                
>R tin - Delete the record that has "tin" as a key. Print one of the lines 

>>"Key tin deleted" 

>> OR

>>"Key tin not present".
                                
>S fortune - Search for the key "fortune". Print one of the lines 

>>"Key fortune:" then the record corresponding to that key, 

>> OR

>> "Key fortune not found".
                               
>M - Print the line "Membership is M" where M is the number of records stored in the table.
                                
>P - Print a list of the elements in the Table in the order of their position in the table,
    one record per line in the form "# key:Record"
                                
>T - Print the following three lines where the values are rounded to one decimal place.

>>    Average probes in insert = #.#

>>    Average probes in unsuccessful search = #.#

>>    Average probes in successful search = #.#
                                
>E - End of file
                                
