package com.example.osid.POJOs;

public class User {
    int id;
    int edad;
    float peso;
    float PGPU; // promedio glucosa por unidad
    float basal;
    float insulinaRestante;
    boolean gender;
    String nombre, primerApellido, segundoApellido;

    public User(){

    }

    public User(int edad, float peso, float basal, boolean gender, String nombre, String primerApellido, String segundoApellido, float PGPU, float insulinaRestante)  {
        this.edad = edad;
        this.peso = peso;
        this.basal = basal;
        this.gender = gender;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.PGPU = PGPU;
        this.insulinaRestante = insulinaRestante;
    }

    public User(int id, String nombre, String primerApellido, String segundoApellido, int edad, float peso, float basal, boolean gender, float PGPU, float insulinaRestante) {
        this.id = id;
        this.edad = edad;
        this.peso = peso;
        this.basal = basal;
        this.gender = gender;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.PGPU = PGPU;
        this.insulinaRestante = insulinaRestante;
    }

    public User(User user){
        this.id = user.id;
        this.edad = user.edad;
        this.peso = user.peso;
        this.basal = user.basal;
        this.gender = user.gender;
        this.nombre = user.nombre;
        this.primerApellido = user.primerApellido;
        this.segundoApellido = user.segundoApellido;
        this.PGPU = user.PGPU;
        this.insulinaRestante = user.insulinaRestante;
    }

    public void  copyUser(User user) {
        this.id = user.id;
        this.edad = user.edad;
        this.peso = user.peso;
        this.basal = user.basal;
        this.gender = user.gender;
        this.nombre = user.nombre;
        this.primerApellido = user.primerApellido;
        this.segundoApellido = user.segundoApellido;
        this.PGPU = user.PGPU;
        this.insulinaRestante = user.insulinaRestante;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getBasal() {
        return basal;
    }

    public void setBasal(float basal) {
        this.basal = basal;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    public void setPGPU(float PGPU){
        this.PGPU = PGPU;
    }

    public  float getPGPU(){
        return PGPU;
    }

    public void setInsulinaRestante(float insulinaRestante){
        this.insulinaRestante = insulinaRestante;
    }
    public  float getInsulinaRestante(){
        return insulinaRestante;
    }
}
