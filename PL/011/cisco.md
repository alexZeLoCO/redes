# Packet Tracer
## Comandos

- `enable` Pasa a modo privilegiado
- `config` Pasa a modo de configuracion
- `interface <interfaz>` Selecciona la interfaz a configurar
- `ip address <ip> <mask>` Selecciona la ip de la interfaz
- `no shutdown` arranca la interfaz configurada. El opuesto es `shutdown`, que apagara la interfaz
- `exit` retrocede al nivel anterior
- Un '?' al final de un comando solicita que PacketTracer ofrezca las posibles opciones

```
enable
config
interface FastEthernet 0/0
ip address 192.168.0.1 255.255.255.0
no shutdown
```