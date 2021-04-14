package math;

public class Torus_Math {

	
	

	private static float R = 1;
	private static float P = 0.2f;
	
	
	
	private static float[] generateTorusPositions(int Uindices, int Vindices) {
		float[] positions = new float[Uindices * Vindices * 3];
		float Uval, Vval=0;
		for (int u=0;u<Uindices;u++) {
			Uval = (float)((u/(float)Uindices)*2  * Math.PI);
			for (int v=0;v<Vindices;v++) {
				Vval = (float)(Math.PI*2* (v/(float)(Vindices-1)));
				positions[(u*Vindices + v)*3 +0] = getTorusX(Uval, Vval);//X
				positions[(u*Vindices + v)*3 +1] = getTorusY(Uval, Vval);//Y
				positions[(u*Vindices + v)*3 +2] = getTorusZ(Uval, Vval);//Z
			}
		}
		return positions;
	}
	
	private static float[] generateTorusNormals(int Uindices, int Vindices) {
		float[] normals = new float[Uindices * Vindices * 3];
		float Uval, Vval=0;
		
		float t1x, t1y, t1z;
		float x, y, z;
		for (int u=0;u<Uindices;u++) {
			Uval = (float)((u/(float)Uindices)*2  * Math.PI);
			for (int v=0;v<Vindices;v++) {
				Vval = (float)(Math.PI*2* (v/(float)(Vindices-1)));
				t1x = (float)-Math.cos(Vval+Math.PI/2f);
				t1y = (float)Math.sin(Vval+Math.PI/2f);
				t1z = 0;
				x = (float)(Math.cos(-Uval)*t1x 	+ 0*t1y 	+ 	Math.sin(-Uval)*t1z);
				y = (float)(0*t1x					+ 1*t1y		+	0*t1z);
				z = (float)(-Math.sin(-Uval)*t1x 	+ 0*t1y 	+ 	Math.cos(-Uval)*t1z);
				normals[(u*Vindices + v)*3 +0] = x;//X
				normals[(u*Vindices + v)*3 +1] = y;//Y
				normals[(u*Vindices + v)*3 +2] = z;//Z
			}
		}
		return normals;
	}
	
	private static int[] generateTorusIndicies(int uindex, int vindex) {
		boolean dot = true;//Anderes Muster
		int triangles = (vindex-1) * 2 * (uindex);
		int[] triangleindicies = new int[triangles*3];
		int currentindex=0;
		//Laufe ueber alle Dreiecke und verbinde Punkte anhand der U und V variablen
		for (int i=0;i<uindex-1;i++) {
			for (int v=0;v<vindex-1;v++) {
				triangleindicies[currentindex] = v + i*vindex;
				currentindex++;
				if (dot)triangleindicies[currentindex] = v + i*vindex + vindex;
				else triangleindicies[currentindex] = v + i*vindex + 1;
				currentindex++;
				if (dot)triangleindicies[currentindex] = v + i*vindex + 1;
				else triangleindicies[currentindex] = v + i*vindex + vindex;
				currentindex++;
			}
			for (int v=0;v<vindex-1;v++) {
				triangleindicies[currentindex] = v + i*vindex + 1;
				currentindex++;
				triangleindicies[currentindex] = v + i*vindex + vindex;
				currentindex++;
				triangleindicies[currentindex] = v + i*vindex + vindex +1;
				currentindex++;
			}
		}
		for (int v=0;v<vindex-1;v++) {
			if (v!=0)triangleindicies[currentindex] = v + 0*vindex-1;
			else triangleindicies[currentindex] = v+vindex-2;
			currentindex++;
			if (v!=0)triangleindicies[currentindex] = v + 0*vindex;
			else triangleindicies[currentindex] = (int)(v + vindex-1);
			currentindex++;
			if (v!=0)triangleindicies[currentindex] = -(vindex-v) + (uindex)*vindex-1;
			else triangleindicies[currentindex] = -(vindex-v) + (uindex+1)*vindex-2;
			currentindex++;
		}
		for (int v=0;v<vindex-1;v++) {
			triangleindicies[currentindex] = (vindex-v) + (uindex-1)*vindex-2 ; 
			currentindex++;
			if (v!=0)triangleindicies[currentindex] = -v + 1*vindex-1;
			else triangleindicies[currentindex] = -v + vindex-1;
			currentindex++;
			triangleindicies[currentindex] = (vindex-v) + (uindex-1)*vindex-1;
			currentindex++;
		}			
		return triangleindicies;
	}
	
	
	private  static float getTorusX(float u, float v) {
		return (float)((R + P*Math.sin(v)  )*Math.cos(u));
	}

	private static float getTorusZ(float u,float v) {
		return (float)((R + P*Math.sin(v)  )*Math.sin(u));
	}

	private static float getTorusY(float u, float v) {
		return (float)  (P*Math.cos(v));
	}
	
	
	private static float[] generateTorusColors(int Uindex, int Vindex) {
		float[] colors = new float[Uindex * Vindex * 3];
		for (int u=0;u<Uindex;u++) {
			for (int v=0;v<Vindex;v++) {
				colors[(u*Vindex + v)*3 +0] = Math.abs(2*(u/(float)(Uindex-1))-1);//X
				colors[(u*Vindex + v)*3 +1] = 0;//Y
				colors[(u*Vindex + v)*3 +2] = Math.abs(2*(v/(float)(Vindex-1))-1);//Z
			}
		}
		return colors;
	}
	
	private static float[] generateTorusTexCoords(int Uindex, int Vindex) {
		float[] colors = new float[Uindex * Vindex * 3];
		for (int u=0;u<Uindex;u++) {
			for (int v=0;v<Vindex;v++) {
				colors[(u*Vindex + v)*3 +0] = 1-(u/(float)(Uindex-1));//X
				colors[(u*Vindex + v)*3 +1] = 1-(v/(float)(Vindex-1));//Y
				colors[(u*Vindex + v)*3 +2] = 0;//Z
			}
		}
		return colors;
	}
}
