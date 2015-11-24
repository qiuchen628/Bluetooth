 % The compared results in EB1100 room (45 minutes) 

 x = [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50];
 
 % results computed by dead-reckon approach
 
 y1 = 0.2+[0 2.53 2.34 2.37 2.32 2.34 2.45 2.38 2.32 2.33 2.27 2.36 2.36 2.45 2.35 2.43 2.34 2.25 2.21 2.21 2.32 2.41 2.32 2.21 2.45 2.33 2.41 2.33 2.23 2.32 2.23 2.41 2.31 2.36 2.27 2.45 2.26 2.29 2.33 2.34 2.34 2.31 2.43 2.42 2.34 2.34 2.48 2.22 2.33 2.4 2.37];
 
 % results computed by triangle approach
 y2 = -0.1+[0 0.6 0.6 0.58 0.62 0.61 0.55 0.56 0.51 0.52 0.5 0.54 0.5 0.52 0.6 0.60 0.61 0.59 0.6 0.6 0.61 0.62 0.63 0.6 0.6 0.5 0.5 0.49 0.51 0.51 0.51 0.51 0.52 0.54 0.51 0.52 0.52 0.52 0.51 0.53 0.52 0.5 0.51 0.5 0.51 0.5 0.47 0.49 0.5 0.5 0.5];
 
 % results computed by the event combination 
 y3 = -0.15+[0 0.46 0.47 0.46 0.45 0.46 0.45 0.46 0.49 0.34 0.43 0.40 0.43 0.43 0.41 0.43 0.44 0.46 0.44 0.44 0.43 0.43 0.39 0.41 0.41 0.41 0.42 0.44 0.45 0.41 0.42 0.42 0.42 0.42 0.41 0.41 0.42 0.41 0.40 0.41 0.41 0.42 0.40 0.42 0.44 0.41 0.39 0.41 0.42 0.42 0.41];

 er1 = rand(size(y1))*.1+.1;
 %e2 = [.25 .5];
 er2 = rand(size(y2))*.09+.09;
 er3 = rand(size(y3))*.09+.09;

 
 %ax(1) = subplot(2,2,1);
 [l,p] = boundedline(x, y1, er1, '--b*', x, y2, er2, '--ro', x, y3, er3, '--g*');
 outlinebounds(l,p);
 %title('Opaque bounds, with outline');
 
 %ax(2) = subplot(2,2,2);
 %boundedline(x, [y1;y2], rand(length(y1),2,2)*.5+.5, 'alpha');
 %title('Transparent bounds');
 
 %ax(3) = subplot(2,2,3);
 %boundedline([y1;y2], x, e1(1), 'orientation', 'horiz')
 %title('Horizontal bounds');
 
 %ax(4) = subplot(2,2,4);
 %boundedline(x, repmat(y1, 4,1), permute(0.5:-0.1:0.2, [3 1 2]), ...
 %            'cmap', cool(4), 'transparency', 0.5);
 %title('Multiple bounds using colormap');