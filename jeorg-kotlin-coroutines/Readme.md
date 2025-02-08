# JEOrg :: KTD :: Coroutines :: Parent



# Dispatchers - Default vs IO
| Feature                | 	Dispatchers.Default	           | Dispatchers.IO                    |
|------------------------|---------------------------------|-----------------------------------|
| Optimized for	         | CPU-bound tasks	                | I/O-bound tasks                   |
| Thread pool size	      | Fixed to CPU cores (max 64)	    | Starts small, grows as needed     |
| Handles blocking?      | 	❌ Not optimized for blocking	  | ✅ Expands for blocking I/O        |
| Work-stealing?	        | ✅ Yes, improves CPU efficiency	 | ❌ Not needed, threads mostly wait |
| Extra thread creation? | 	❌ No (prevents overhead)       | 	✅ Yes (prevents I/O bottlenecks) |

## Resources

### Books

-   Moskala, M. (2021 - 2022). <i>Kotlin Coroutines DEEP DIVE</i>. (First Edition). Kt. Academy
-   Vasic, M. (21st May 2018). <i>Building Applications with Spring 5 and Kotlin</i>. (First Edition). Packt Publishing
-   Griffiths, D. Griffiths, D. (February 2019). <i>Head First A Brain-Friendly Guide</i>. (First Edition). O'Reilly
-   Skeen, J. Greenhalgh, D. (July 2018). <i>Kotlin Programming - The Big Nerd Ranch Guide</i>. (First Edition). Big Nerd Ranch
-   Jemerov, D. Isakova, S. (2017). <i>Kotlin in Action</i>. (First Edition). Manning Publications

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
