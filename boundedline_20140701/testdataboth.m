 % this .m file descrip the data of x-time(seconds), the deviation made by dead-reckoning, our app and combining results.

 x = [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50]*10;
 
 % results computed by dead-reckon approach
 
 y1 = [0.04 0.35 3.34 5.78 3.3 3.71 4.23 3.62 4.01 4.15 4.65 3.34 3.67 3.98 4.12 4.35 5 4.01 2.01 2.45 3.22 3.45 3.56 3.89 4.11 1.23 1.56 1.89 2.31 2.45 3.01 5.23 5.56 5.78 6.23 3.21 0.21 3.65 4.01 6.02 9.3 1.03 8.13 0.22 2.12 2.6 3.2 3.43 0.59 3.8];

 
 % results computed by law of cosine and RSSI
 
 y2 = [0.5 0.5 0.6 0.6 0.6 0.6 0.6 0.5 0.5 0.5 0.5 0.4 0.4 0.4 0.3 0.3 0.3 0.3 0.5 0.5 0.4 0.3 0.3 0.3 0.4 0.5 0.5 0.5 0.5 0.5 0.5 0.4 0.4 0.3 0.3 0.5 0.5 0.5 0.5 0.4 0.4 0.4 0.4 0.4 0.7 0.7 0.7 0.7 0.7 0.7];

 
 % results computed by the event combination 
 y3 = [0.04 0.35 0.51 0.62 0.89 1.14 0.33 0.5 0.67 0.45 0.77 0.42 0.45 0.47 0.35 0.36 0.28 0.32 0.53 0.54 0.45 0.35 0.39 0.38 0.35 0.56 0.6 0.46 0.45 0.52 0.81 0.45 0.54 0.42 0.43 0.54 0.21 0.43 0.42 0.45 0.45 0.46 0.48 0.22 0.73 0.87 0.9 0.56 0.59 0.65];

 
Y5 = -2:13:13; X5 = 50 * ones(size(Y5));
Y8 = -2:13:13; X8 = 80 * ones(size(Y8));
Y12 = -2:13:13; X12 = 120 * ones(size(Y12));
Y15 = -2:13:13; X15 = 150 * ones(size(Y15));
Y19 = -2:13:13; X19 = 190 * ones(size(Y19));
Y22 = -2:13:13; X22 = 220 * ones(size(Y22));
Y26 = -2:13:13; X26 = 260 * ones(size(Y26));
Y32 = -2:13:13; X32 = 320 * ones(size(Y32));
Y36 = -2:13:13; X36 = 360 * ones(size(Y36));
Y40 = -2:13:13; X40 = 400 * ones(size(Y40));
Y45 = -2:13:13; X45 = 450 * ones(size(Y45));

 e1 = rand(size(y1))*.5+.5;
 %e2 = [.25 .5];
 e2=rand(size(y2))*.5+.5;
 e3 =rand(size(y3))*.5+.5;
 
[l,p] = boundedline(x, y1, e1, '--b*', x, y2, e2, '--ro', x, y3, e3, '--g*'); 
  
 
 
 
 
 
 outlinebounds(l,p);
 
 
 
 hold on; plot(X5, Y5,'--'); 
 plot(X8, Y8,'--');
   hold on; plot(X12, Y12,'--');
    hold on; plot(X15, Y15,'--');
     hold on; plot(X19, Y19,'--');
      hold on; plot(X22, Y22,'--');
       hold on; plot(X26, Y26,'--');
        hold on; plot(X32, Y32,'--');
        hold on; plot(X36, Y36,'--');
        
       hold on; plot(X40, Y40,'--');
        hold on; plot(X45, Y45,'--');