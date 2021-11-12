package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    //Se crean nodos para cada elemento del sistema solar 
    Node sol_nodo = new Node("sol_nodo");
    Node mercurio_nodo = new Node("mercurio_nodo");
    Node venus_nodo = new Node("venus_nodo");
    Node tierra_nodo = new Node("tierra_nodo");
    Node marte_nodo = new Node("marte_nodo");
    
    //Se crean espaciales para los elementos del sistema solar para rotar a 
    //los planetas sobre su propio eje
    Spatial sSol=null;
    Spatial sMercurio=null;
    Spatial sVenus=null;
    Spatial sTierra=null;
    Spatial sMarte=null;

    public static void main(String[] args) {
        //Cambiamos la configuración de la ventana
        AppSettings settings = new AppSettings(true);
        settings.setTitle("Examen"); 
        settings.setResolution(1280, 720);
        Main app = new Main();
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        //Vector para situar la posición de la cámara y ver mejor a los elementos
        Vector3f vector = new Vector3f(0,0,50);
        cam.setLocation(vector);
        System.out.print(cam.getLocation());
        
        /**
         * Se crean esferas para cada elemento del sistema solar para poderlos representar en 3D
         * Así también se crean sus material, geometrías, se les asigna una textura correspondiente a cada elemento y 
         * se les aplica una traslación y rotación para que tomen su lugar correspondiente y se vean adecuadamente
         * simulando parte del sistema solar
         * 
         * Las geometrías de cada planeta y el sol se agregan a el nodo que lo representa
        **/
        
        Sphere sol = new Sphere(20,20,5);
        Geometry sol_geom = new Geometry("sol", sol);
        Material sol_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        sol_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/sun.jpg"));
        sol_geom.setMaterial(sol_mat);
        sol_geom.rotate(-FastMath.HALF_PI,0,0);
        sol_nodo.attachChild(sol_geom);
        
        Sphere mercurio = new Sphere(20,20,1);
        Geometry mercurio_geom = new Geometry("mercurio", mercurio);
        Material mercurio_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mercurio_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/mercury.jpg"));
        mercurio_geom.setMaterial(mercurio_mat);
        mercurio_geom.rotate(-FastMath.HALF_PI,0,0);
        mercurio_geom.setLocalTranslation(8,0,0);
        mercurio_nodo.attachChild(mercurio_geom);
        
        Sphere venus = new Sphere(20,20,2);
        Geometry venus_geom = new Geometry("venus", venus);
        Material venus_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        venus_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/venus.jpg"));
        venus_geom.setMaterial(venus_mat);
        venus_geom.rotate(-FastMath.HALF_PI,0,0);
        venus_geom.setLocalTranslation(14,0,0);
        venus_nodo.attachChild(venus_geom);
        
        Sphere tierra = new Sphere(20, 20, 3);
        Geometry tierra_geom = new Geometry("tierra", tierra);
        Material tierra_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        tierra_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/earth.jpg"));
        tierra_geom.setMaterial(tierra_mat);
        tierra_geom.rotate(-FastMath.HALF_PI,0,0);
        tierra_geom.setLocalTranslation(22, 0, 0);
        tierra_nodo.attachChild(tierra_geom);
        
        Sphere marte = new Sphere(20, 20, 2.5f);
        Geometry marte_geom = new Geometry("marte", marte);
        Material marte_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        marte_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/mars.jpg"));
        marte_geom.setMaterial(marte_mat);
        marte_geom.rotate(-FastMath.HALF_PI,0,0);
        marte_geom.setLocalTranslation(31, 0, 0);
        marte_nodo.attachChild(marte_geom);
        
        
        //Los nodos de cada planeta se agregan al nodo del sol para tener una estructura organizada
        sol_nodo.attachChild(mercurio_nodo);
        sol_nodo.attachChild(venus_nodo);
        sol_nodo.attachChild(tierra_nodo);
        sol_nodo.attachChild(marte_nodo);
        //El rootNode tiene como hijo al sol_nodo
        rootNode.attachChild(sol_nodo);
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        
        /**
         * En esta primera parte Se comprueba si los espaciales no estan inicializados 
         * se obtiene la geometría de los elementos y se asignan a un Spacial correspondiente y así 
         * generar el movimiento de rotación sobre su propio eje utilizando .rotate();
         * 
         * El sol rota más lento que la tierra y entre más cerca este un planeta del sol
         * más rapido es su movimiento de rotación sobre su propio eje
         */
        
        if (sSol ==null) 
            sSol = rootNode.getChild("sol");
        sSol.rotate(0,0,tpf/2);
        
        if (sMercurio ==null) 
            sMercurio = rootNode.getChild("mercurio");
        sMercurio.rotate(0,0,4*tpf);
        
        if (sVenus ==null) 
            sVenus = rootNode.getChild("venus");
        sVenus.rotate(0,0,3*tpf);
        
        if (sTierra ==null) 
            sTierra = rootNode.getChild("tierra");
        sTierra.rotate(0,0,2*tpf);
        
        if (sMarte ==null) 
            sMarte = rootNode.getChild("marte");
        sMarte.rotate(0,0,tpf);
        
        /**
         * A los nodo que representan un planeta se rotan para simular el 
         * movimiento de traslación que tienen los planetas
         * 
         * Entre más cerca este el planeta del sol su movimiento de traslación
         * es más rápido
         */
        
        mercurio_nodo.rotate(0,5*tpf,0);
        venus_nodo.rotate(0,3*tpf,0);
        tierra_nodo.rotate(0,tpf,0);
        marte_nodo.rotate(0,tpf/2,0);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
