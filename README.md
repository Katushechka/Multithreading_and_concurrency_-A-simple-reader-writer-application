# Multithreading_and_concurrency_-A-simple-reader-writer-application


**The main goals of this project are:**

• Handling communication between threads using a simple writer/reader pattern.

• Observing the behavior of non-synchronized threads.

• Learning how multiple threads can be synchronized when accessing a shared resource.











**The following requirement are for the project:**

• The character buffer should hold only ONE character.

• The buffer also has a bool variable like “HasCharacter”.

• NOTE! This bool value should not be tested by a property, it should ONLY be tested Inside the critical section.

• In the Non-synchronous mode, only simple get/set should be done.

• All synchronization takes place in buffer, NOT in writer/reader.

• The reader should put all the read characters back into another string.

• NO sleep in buffer.



*For both writing and reading, threads are used. The writer thread puts a character from a text source to the buffer while the
reader reads from the buffer.*
