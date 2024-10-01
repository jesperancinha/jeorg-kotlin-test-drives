include ../Makefile.mk

first:
	make b

b: build
build: ./jeorg*
		for d in $^ ; do \
  			cd $${d}; \
  			make b; \
  			cd ..; \
		done
