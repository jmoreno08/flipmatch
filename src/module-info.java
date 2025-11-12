/**
 * Módulo del juego Flip & Match.
 *
 * <p>Expone los paquetes de aplicación para su uso por JavaFX.
 */

module PrubaFX {
    requires javafx.controls;

    exports application;
    exports application.controller;
    exports application.model;
    exports application.service;
}