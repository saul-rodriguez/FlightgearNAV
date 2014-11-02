# androidnav.nas


var androidwp = func {
 #print("NAVANDROID: copying wp coordinates");

 #WP0
 var newcoord = getprop("/autopilot/route-manager/route/wp/latitude-deg");
 if (newcoord != nil) {
    setprop("/androidnav/wp0_lat",newcoord);
 }
 
 var newcoord = getprop("/autopilot/route-manager/route/wp/longitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp0_lon",newcoord);
 }

 #WP1
 var newcoord = getprop("/autopilot/route-manager/route/wp[1]/latitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp1_lat",newcoord);
 }

 var newcoord = getprop("/autopilot/route-manager/route/wp[1]/longitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp1_lon",newcoord);
 }

 #WP2
 var newcoord = getprop("/autopilot/route-manager/route/wp[2]/latitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp2_lat",newcoord);
 }

 var newcoord = getprop("/autopilot/route-manager/route/wp[2]/longitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp2_lon",newcoord);
 }

#WP3
 var newcoord = getprop("/autopilot/route-manager/route/wp[3]/latitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp3_lat",newcoord);
 }

 var newcoord = getprop("/autopilot/route-manager/route/wp[3]/longitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp3_lon",newcoord);
 }

#WP4
 var newcoord = getprop("/autopilot/route-manager/route/wp[4]/latitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp4_lat",newcoord);
 }

 var newcoord = getprop("/autopilot/route-manager/route/wp[4]/longitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp4_lon",newcoord);
 }

#WP5
 var newcoord = getprop("/autopilot/route-manager/route/wp[5]/latitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp5_lat",newcoord);
 }

 var newcoord = getprop("/autopilot/route-manager/route/wp[5]/longitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp5_lon",newcoord);
 }

#WP6
 var newcoord = getprop("/autopilot/route-manager/route/wp[6]/latitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp6_lat",newcoord);
 }

 var newcoord = getprop("/autopilot/route-manager/route/wp[6]/longitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp6_lon",newcoord);
 }

#WP7
 var newcoord = getprop("/autopilot/route-manager/route/wp[7]/latitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp7_lat",newcoord);
 }

 var newcoord = getprop("/autopilot/route-manager/route/wp[7]/longitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp7_lon",newcoord);
 }

#WP8
 var newcoord = getprop("/autopilot/route-manager/route/wp[8]/latitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp8_lat",newcoord);
 }

 var newcoord = getprop("/autopilot/route-manager/route/wp[8]/longitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp8_lon",newcoord);
 }

#WP9
 var newcoord = getprop("/autopilot/route-manager/route/wp[9]/latitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp9_lat",newcoord);
 }

 var newcoord = getprop("/autopilot/route-manager/route/wp[9]/longitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp9_lon",newcoord);
 }

#WP10
 var newcoord = getprop("/autopilot/route-manager/route/wp[10]/latitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp10_lat",newcoord);
 }

 var newcoord = getprop("/autopilot/route-manager/route/wp[10]/longitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp10_lon",newcoord);
 }

#WP11
 var newcoord = getprop("/autopilot/route-manager/route/wp[11]/latitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp11_lat",newcoord);
 }

 var newcoord = getprop("/autopilot/route-manager/route/wp[11]/longitude-deg");
 if (newcoord != nil) {
   setprop("/androidnav/wp11_lon",newcoord);
 }


 settimer(androidwp,1); #trigger mycode every 1 second
}



#Create the auxiliary coordinates and wait until all other systems are loaded 
var wait_and_start = func {
 printf("NAVANDROID: Init coordinates");

 setprop("/androidnav/wp0_lat",0.0);
 setprop("/androidnav/wp0_lon",0.0);

 setprop("/androidnav/wp1_lat",0.0);
 setprop("/androidnav/wp1_lon",0.0);

 setprop("/androidnav/wp2_lat",0.0);
 setprop("/androidnav/wp2_lon",0.0);

 setprop("/androidnav/wp3_lat",0.0);
 setprop("/androidnav/wp3_lon",0.0);

 setprop("/androidnav/wp4_lat",0.0);
 setprop("/androidnav/wp4_lon",0.0);
 
 setprop("/androidnav/wp5_lat",0.0);
 setprop("/androidnav/wp5_lon",0.0);

 setprop("/androidnav/wp6_lat",0.0);
 setprop("/androidnav/wp6_lon",0.0);

 setprop("/androidnav/wp7_lat",0.0);
 setprop("/androidnav/wp7_lon",0.0);

 setprop("/androidnav/wp8_lat",0.0);
 setprop("/androidnav/wp8_lon",0.0);

 setprop("/androidnav/wp9_lat",0.0);
 setprop("/androidnav/wp9_lon",0.0);

 setprop("/androidnav/wp10_lat",0.0);
 setprop("/androidnav/wp10_lon",0.0);

 setprop("/androidnav/wp11_lat",0.0);
 setprop("/androidnav/wp11_lon",0.0);

 settimer(androidwp,10);
}

_setlistener("/sim/signals/nasal-dir-initialized", wait_and_start);


