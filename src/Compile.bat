@echo off
javac MONIP2.java
java MONIP2 < d0inD.txt > ../bin/myd0D_01.out
java MONIP2 < d0inL.txt > ../bin/myd0L_01.out
java MONIP2 < d0inQ.txt > ../bin/myd0Q_01.out
java MONIP2 < d1inD.txt > ../bin/myd1D_01.out
java MONIP2 < d1inL.txt > ../bin/myd1L_01.out
java MONIP2 < d1inQ.txt > ../bin/myd1Q_01.out
java MONIP2 < d2inD.txt > ../bin/myd2D_01.out
java MONIP2 < d2inL.txt > ../bin/myd2L_01.out
java MONIP2 < d2inQ.txt > ../bin/myd2Q_01.out

fc d0outD.txt myd0D_01.out > ../bin/errors/errors_d0D_01.txt
fc d0outL.txt myd0L_01.out > ../bin/errors/errors_d0L_01.txt
fc d0outQ.txt myd0Q_01.out > ../bin/errors/errors_d0Q_01.txt
fc d1outD.txt myd1D_01.out > ../bin/errors/errors_d1D_01.txt
fc d1outL.txt myd1L_01.out > ../bin/errors/errors_d1L_01.txt
fc d1outQ.txt myd1Q_01.out > ../bin/errors/errors_d1Q_01.txt
fc d2outD.txt myd2D_01.out > ../bin/errors/errors_d2D_01.txt
fc d2outL.txt myd2L_01.out > ../bin/errors/errors_d2L_01.txt
fc d2outQ.txt myd2Q_01.out > ../bin/errors/errors_d2Q_01.txt
