package edu.rico.tutorial.tutorialclase.navigation;

public enum ViewType {
    PERFIL("/edu/rico/tutorial/tutorialclase/views/perfil-view.fxml", "Configurador de Perfil Dinámico"),
    DASHBOARD("/edu/rico/tutorial/tutorialclase/views/dashboard-view.fxml", "Panel de Control Principal"),
    ESTADISTICAS("/edu/rico/tutorial/tutorialclase/views/estadisticas-view.fxml", "Métricas y Analítica");

    private final String fxmlPath;
    private final String title;

    ViewType(String fxmlPath, String title) {
        this.fxmlPath = fxmlPath;
        this.title = title;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public String getTitle() {
        return title;
    }
}