import java.util.ArrayList;
import java.util.List;

// Singleton - Aquecedor
class Aquecedor {
    private static Aquecedor instance;
    private boolean ligado;

    private Aquecedor() {
        ligado = false;
    }

    public static Aquecedor getInstance() {
        if (instance == null) {
            instance = new Aquecedor();
        }
        return instance;
    }

    public void ligar() {
        ligado = true;
        System.out.println("Aquecedor ligado");
    }

    public void desligar() {
        ligado = false;
        System.out.println("Aquecedor desligado");
    }
}

// Singleton - Ar condicionado
class ArCondicionado {
    private static ArCondicionado instance;
    private boolean ligado;

    private ArCondicionado() {
        ligado = false;
    }

    public static ArCondicionado getInstance() {
        if (instance == null) {
            instance = new ArCondicionado();
        }
        return instance;
    }

    public void ligar() {
        ligado = true;
        System.out.println("Ar condicionado ligado");
    }

    public void desligar() {
        ligado = false;
        System.out.println("Ar condicionado desligado");
    }
}

// Observer - Sensor de temperatura
interface TemperaturaObserver {
    void temperaturaMudou(double temperatura);
}

class SensorTemperatura {
    private double temperatura;
    private List<TemperaturaObserver> observers;

    public SensorTemperatura() {
        temperatura = 0.0;
        observers = new ArrayList<>();
    }

    public void adicionarObserver(TemperaturaObserver observer) {
        observers.add(observer);
    }

    public void removerObserver(TemperaturaObserver observer) {
        observers.remove(observer);
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
        notificarObservers();
    }

    private void notificarObservers() {
        for (TemperaturaObserver observer : observers) {
            observer.temperaturaMudou(temperatura);
        }
    }
}

// Adapter - Conversão de temperatura
interface TemperaturaAdapter {
    double converter(double temperatura);
}

class CelsiusParaKelvinAdapter implements TemperaturaAdapter {
    @Override
    public double converter(double temperatura) {
        return temperatura + 273.15;
    }
}

// Classe principal
public class Main {
    public static void main(String[] args) {
        // Instanciando os objetos
        Aquecedor aquecedor = Aquecedor.getInstance();
        ArCondicionado arCondicionado = ArCondicionado.getInstance();
        SensorTemperatura sensorTemperatura = new SensorTemperatura();

        // Adapter
        TemperaturaAdapter celsiusParaKelvinAdapter = new CelsiusParaKelvinAdapter();

        // Observer - Aquecedor
        sensorTemperatura.adicionarObserver(new TemperaturaObserver() {
            @Override
            public void temperaturaMudou(double temperatura) {
                if (temperatura < 15) {
                    aquecedor.ligar();
                } else if (temperatura > 22) {
                    aquecedor.desligar();
                }
                
            }
        });

        // Observer - Ar condicionado
        sensorTemperatura.adicionarObserver(new TemperaturaObserver() {
            @Override
            public void temperaturaMudou(double temperatura) {
                if (temperatura > 29) {
                    arCondicionado.ligar();
                } else if (temperatura < 15) {
                    arCondicionado.desligar();
                }
                
            }
        });

        // Simulando mudança de temperatura
        

        // Adapter - Exemplo de conversão de temperatura
        double temperaturaCelsius = 10;

        sensorTemperatura.setTemperatura(temperaturaCelsius);

        double temperaturaKelvin = celsiusParaKelvinAdapter.converter(temperaturaCelsius);

        System.out.println("Temperatura em Celsius: " + temperaturaCelsius);
        System.out.println("Temperatura em Kelvin: " + temperaturaKelvin);
    }
}
