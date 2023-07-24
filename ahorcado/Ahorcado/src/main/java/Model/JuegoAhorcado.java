/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Interfaz.AhorcadoGUI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import lombok.*;


@ToString
@AllArgsConstructor
@Getter
@Setter

/**
 *
 * @author USUARIO
 */
public class JuegoAhorcado {
    private ArrayList<Palabra> palabras;
    private String palabraSecreta;
    private String descripcion;
    private int intentosRestantes;
    private Set<Character> letrasAdivinadas;
    private int score;
    private AhorcadoGUI ventanaJuego;
    
    
 

    public JuegoAhorcado(AhorcadoGUI ventanaJuego) {
        palabras = generarPalabras();
        reiniciarJuego();
        this.ventanaJuego = ventanaJuego;
    }

    public void reiniciarJuego() {
        if (palabras.size() > 0) {
            Random rand = new Random();
            int index = rand.nextInt(palabras.size());
            Palabra palabra = palabras.get(index);

            palabraSecreta = palabra.getPalabra();
            descripcion = palabra.getDescripcion();
        } else {
            palabraSecreta = "";
            descripcion = "";
        }

        intentosRestantes = 5;
        letrasAdivinadas = new HashSet<>();
        score = 0;
    }

   public boolean intentar(char letra) {

    letra = Character.toLowerCase(letra); // Convertir letra a minúscula
    boolean adivino = false;
    if (!letrasAdivinadas.contains(letra)) {
        letrasAdivinadas.add(letra);
        if (getPalabraSecreta().toLowerCase().indexOf(letra) >= 0) { // Comparar con la palabra secreta en minúscula
            adivino = true;
            incrementarPuntaje(1);
        } else {
            intentosRestantes--;
        }
    }
     ventanaJuego.actualizarImagen(intentosRestantes);
    return adivino;
}

    public boolean gano() {
         String palabraSecretaLowerCase = palabraSecreta.toLowerCase();
        for (char letra : palabraSecretaLowerCase.toCharArray()) {
            if (!letrasAdivinadas.contains(letra)) {
                return false;
            }
        }
        return true;
    }

    public boolean perdio() {
        return intentosRestantes == 0;
    }

    public String estadoActual() {
        StringBuilder sb = new StringBuilder();
        for (char letra : palabraSecreta.toCharArray()) {
            if (letrasAdivinadas.contains(letra)) {
                sb.append(letra);
            } else {
                sb.append("_");
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public void incrementarPuntaje(int puntos) {
       
        score += puntos;
    }
private ArrayList<Palabra> generarPalabras() {
        ArrayList<Palabra> palabras = new ArrayList<>();
        palabras.add(new Palabra("Windows", "Un sistema operativo"));
        palabras.add(new Palabra("Linux", "Un sistema operativo de código abierto"));
        palabras.add(new Palabra("SistemasInfo", "Una carrera universitaria"));
        palabras.add(new Palabra("halo", "Campaña que nos estamos pasando"));
        palabras.add(new Palabra("roxy", "mejor novia del mundo"));
        palabras.add(new Palabra("puchis", "nombre de mascota"));

        return palabras;
    }
}
