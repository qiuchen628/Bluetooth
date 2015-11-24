 % The compared results in Hallway (50 minutes) 

 x = [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50];
 
 % results computed by dead-reckon approach
 
 ZBF =[0.15 0.32 0.23 0.45 0.34 0.23 0.13 0.28 0.45 0.43 0.98 0.62 0.23 0.43 0.67 0.45 0.45 0.22 0.31 0.54 0.40 0.62 0.32 0.31 0.2 0.87 0.21 0.15 0.19 0.87 0.44 0.29 0.1 0.11 0.67 0.23 0.69 0.65 0.1 0.21 0.88 0.78 0.45 0.05 0.45 0.13 1.89 0.131 0.56 0.03];
 
 % results computed by law of cosine and RSSI
 ZB = [0.15 0.32 0.23 0.45 0.34 0.23 0.13 0.98 0.45 0.43 0.98 0.62 0.23  1.34  0.67 0.45 0.45 1.54 0.31 1.45 1.34 0.62 0.32 0.31 0.2 0.87 0.21 0.15 0.19 0.87 0.44 0.29 0.1 0.11 0.67 0.23 0.69 0.65 0.1 0.21 0.88 0.78 1.79 0.05 0.45 0.13 0.53 0.43 0.56 0.03];
 
 % results computed by the event combination 
 B =  [0.62 0.88 1.56 2.42 3.56 1.43 0.70 0.4 0.72 2.54 3.45 2.47 0.23  1.74 1.72  0.9 1.95 1.81 2.01 1.74 1.56 1.45 1.71 1.87 0.65 1.57 2.21 0.65 0.19 0.78 0.71 0.81 0.68 2.11 1.78 0.69 0.61 1.65 1.63 1.72 1.81 1.89 1.95 1.88 1.78 1.81 1.12 1.68 1.76 1.81];

 
plot(x,B,'b'); hold on; plot(x,ZB,'r'); hold on;plot(x,ZBF,'g'); hold on;

