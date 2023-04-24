# Comandos Básicos

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

# VLAN
- Dar de alta la VLAN: `Switch(config)# vlan \<NUM\>`
- Asignación de puertos a la VLAN:
	- `Switch(config)# interface \<TYPE\> \<NUM\>/\<MOD\>`
	- `Switch(config-if)# switchport access vlan \<NUM\>`
- Definición de los puertos *trunking*:
	- `Switch(config)# interface \<TYPE\> \<NUM\>/\<MOD\>`
	- `Switch(config-if)# switchport mode trunk`
	- `Switch(config-if)# switchport trunk allowed vlan \<NUM\>\[, \<NUM\>, ...\]`
	- \[`Switch(config-if)# switchport trunk allowed vlan (add/all/except/remove) \<NUM\>\[, \<NUM\>, ...\]`\]
- Ver VLANs configuradas:
	- `Switch(xxx)# show vlan`
	- `Switch(xxx)# show running-config`
	- `Switch(xxx)# show interfaces trunk`
	- `Switch(xxx)# show interfaces status`

Al configurar la vlan trunk hay que dar la vlan de alta a parte y annadirla con `Switch(config-if)# switchport trunk allowed vlan \<NUM\>\[, \<NUM\>, ...\]`

Al crear una VLAN, en el switch se crea un fichero vlan.dat que se carga en el arranque con las VLAN que hayan sido configuradas previamente:

- dir flash:
- delete flash:vlan.dat
- `R1(config)# ipv6 unicast-routing` Habilita al router para utilizar paquetes IPv6
- `R1(config-if)# ipv6 address \<ipv6-address\>/\<prefix-length\>`
- `R1(config-if)# ipv6 address \<ipv6-address\> link-local`

# Switch Layer 3 (3560-24PS)

`switch(config)# ip routing`
`switch(config-if)# switchport access vlan \<VLAN\>`
`switch(config)# interface vlan \<VLAN\>`
`switch(config-if)# ip address \<IP\> \<MASK\>`

# Routing estatico

`router(config)# ip route \<TARGET_IP\> \<TARGET_MASK\> \<NEXT_HOP_IP\>`

# DCHP

Configuración utilizando el router.
| Orden | Emsior | MSG | Significado |
| 1. | Cliente | `DHCPDISCOVER` | "Deseo una dirección" |
| 2. | Servidor | `DHCPOFFER` | "Ofrezco esta dirección" |
| 3. | Cliente | `DHCPREQUEST` | "Acepto esta dirección" |
| 4. | Servidor | `DHCPACK` | "Confirmo la aceptación" |

## DHCPIPv4

### Setup

```
R1(config)# service dhcp
R1(Config)# ip dhcp pool \<pool_name\>
R1(dhcp-config)# network \<ip\> \<mask\>
R1(dhcp-config)# default-router \<ip\>
R1(dhcp-config)# dns-server \<ip\>
R1(dhcp-config)# domain-name \<nombre\>
R1(dhcp-config)# lease \{dias \[horas\] \[minutos\] \\ inf\}
R1(config)# ip dhcp excluded-address \<low\> \[\<high\>\]
```
<em>Nota: La orden `R1(dhcp-config)# lease \{dias \[horas\] \[minutos\] \\ inf\}` es opcional, por defecto es de 1 día.</em>

### Debugging

```
R1# show running-config | section dhcp
R1# show ip dhcp binding
R1# show ip dhcp server statistics
R1# show ip dhcp conflict
```

```
PC1> ipconfig /all
PC1> ipconfig /release
PC1> ipconfig /renew
```

`R1(config)# router rip`
`R1(config-rtr)# version 2`
`R1(config-rtr)# network \<ip\>`
`R1(config-rtr)# passive-interface \<interface\>`
`R1(config-rtr)# no auto-summary`
`R1(config)# show ip route`
`R1(config)# show ip protocols`
`R1# sh ip route`
`R1(config)# ip route \<ip_dest\> \<mask_dest\> <\ip_next_hop\>`

# Routing estático IPv6

- Añadir ruta: `ipv6 route \<ipv5-prefix\>/\<prefix-length\> {ipv6-address|exit-interface}`
- Activar routing: `ipv6 unicast-routing`

# RIPng para IPv6

- `ipv5 router rip \<nombre\>`
- `interface \<interface\>`
- `ipv6 rip \<nombre\> enable`# Listas de acceso

# Access List ACL

## ACL estandar

Chequea las IP origen y destino. Bloquea o permite todo el trafico, no protocolos específicos.
Identificador $\in { [0, 99], [1300, 1900] }$.

## ACL extendida

Chequea las IP origen y destino así como el protocolo. Permite bloquear en función del protocolo.
Identificador $\in { [100, 199], [2000, 2699] }$.

## Inbound / Outbound

Inbound Access List se aplica cuando un paquete va a entrar.

Outbound Access List se aplica cuando un paquete va a salir.

En todas las listas existe una regla implícita que deniega todo lo que no se haya permitido.

- `R1(config)# access-list \<number\> \{permit | deny\} \[protocol\] \<ip\> \<inverted_mask\>`
- `R1(config)# access-list \<number\> \{permit | deny\} \[protocol\] host \<ip\>`
- `R1(config)# access-list \<number\> \{permit | deny\} \[protocol\] any`

Nota: $number \in [1, 99]$
Una `inverted_mask` es la opuesta a la `mask`.
La máscara `255.255.255.0` será la `0.0.0.255`.

La lista `k` tiene prioridad sobre la `k+1`.
Conviene tener los comandos para generar la lista anotados, para poder regenerar la lista.
Es necesario borrar toda la lista `k` para modificar la lista `k`.
`R1(config)# no access-list \<number\>`
`R1(config-if)# ip access-group \<num_lista\> \{in | out\}`

# OSPF 

## OSPF v2

### Setup

1. `R1(config)# router ospf <number>`
2. `R1(config-router)# router-id <id_router>`
3. `R1(config-rotuer)# network <ip> <inverted_mask> area <n_area>`
4. `R1(config-router)# passive interface <interface>`

### Debugging

- `R1# show ip route`
- `R1# show ip protocols`
- `R1# show ip ospf`
- `R1# show ip ospf interface brief`

## OSPF v3

### Setup

- `R1(config)# interface <interface>`
- `R1(config)# ipv6 address <address> link-local`
- `R1(config)# ipv6 unicast-routing`
- `R1(config)# ipv6 router ospf <number>`

# NAT/PAT

## NAT estático

Cada dirección IP interna tiene una dirección IP externa.

`Router(config)# ip nat inside source static \[protocol\] <IP_internal> \[port\] <IP_external> \[PORT\]`
`Router(config)# interface <interface_internal>`
`Router(config-if)# ip nat inside`
`Router(config)# interface <interface_external>`
`Router(config-if)# ip nat outside`

## NAT dinámico

Cada dirección IP interna tiene una dirección IP externa.
Las direcciones se seleccionan automáticamente de la *pool*.

`Router(config)# access-list <n_acl> permit <inner_network> <mask>`
`Router(config)# ip nat pool <name> <first_ip> <last_ip> <netmask <mask> | prefix-length <mask_bits>>`
`Router(config)# ip nat inside source list <n_acl> pool <name>`
`Router(config)# interface <interface_internal>`
`Router(config-if)# ip nat inside`
`Router(config)# interface <interface_external>`
`Router(config-if)# ip nat outside`

## NAT dinámico con sobrecarga

Una dirección IP externa puede estar asociada a varias direcciones IP internas.
Las direcciones se seleccionan automáticamente de la *pool*.

`Router(config)# access-list <num> permit <inner_network> <mask_wildcard>`
`Router(config)# ip nat pool <name> <first_ip> <last_ip> netmask <mask>`
`Router(config)# ip nat inside source list <num>  pool <name> overload`
`Router(config)# interface <interface_internal>`
`Router(config-if)# ip nat inside`
`Router(config)# interface <interface_external>`
`Router(config-if)# ip nat outside`

## PAT

`Router(config)# ip nat inside source static <tcp|udp> <ip_internal> <port_internal> <ip_external> <port_external>`

## Debugging

`Router()# show ip nat translations`
`Router()# show ip nat statistics`

# HSRP (Hot Standy Router Protocol)

Se trata de un router redundante que pueda entrar en activo cuando el router principal caiga.
Es el equivalente a un SAI en el ámbito de las redes.

```
graph LR 
    PC0-->SW0
    PC1-->SW0
    SW0-->R1
    SW0-->R2
    SW0-->RV
    R1-->Internet
    R2-->Internet
    RV-->Internet
``` 

Los equipos PC0 y PC1 tienen como gateway la ip `192.168.1.1`,
pero los router R1 y R2 tienen las IP `192.168.1.101` y `192.168.1.102` respectivamente.

El RV (Router Virtual) tendrá de IP `192.168.1.1`.

```
R1(config)# interface <interface>
R1(config-if)# ip address <ip> <mask>
R1(config-if)# standby version 2
R1(config-if)# standby <id> ip <ip>
R1(config-if)# standby <id> priority <number>
R1(config-if)# standby <id> preempt
```

El id determina el grupo al que pertenece un router. Los routers de un mismo grupo trabajan
entre ellos para asegurar el funcionamiento de la puerta de enlace.

# Trabajo Ingenieria de Redes

## Edificio A

### Plantas
- Planta 1
    - Servidores = 12
- Planta 2
    - Ingenieria = 20
    - RRHH = 4
    - Contabilidad = 10
- Planta 3
    - Dirección = 10
    - Ingenieria = 10
    - RRHH = 4
    - Contabilidad = 5
- Planta 4
    - Dirección = 10
    - Contabilidad = 10
    - Ingenieria = 10

### VLANs
NET: 23.27.24.0
MASK: 255.255.252.0 (22)

#### VLAN 12 'Servidores'
12 equipos => $log_2(12+2) = 4$
MASK: 255.255.255.1111 0000 (240)
NET: 23.27.24.0000 0000 (0)
FIRST: 23.27.24.0000 0001
LAST: 23.27.24.0000 1110
BCAST: 23.27.24.0000 1111

2023:DB8:27:A12::/64

#### VLAN 15 'RRHH'
8 equipos => $log_2(8+2) = 4$
MASK: 255.255.255.1111 0000 (240)
NET: 23.27.24.0001 0000 (16)
FIRST: 23.27.24.0001 0001
LAST: 23.27.24.0001 1110
BCAST: 23.27.24.0001 1111

2023:DB8:27:A15::/64

#### VLAN 13 'Direccion'
20 equipos => $log_2(20+2) = 5$
MASK: 255.255.255.1110 0000 (224)
NET: 23.27.24.0010 0000 (32)
FIRST: 23.27.24.0010 0001
LAST: 23.27.24.0011 1110
BCAST: 23.27.24.0011 1111

2023:DB8:27:A13::/64

#### VLAN 16 'Contabilidad'
25 equipos => $log_2(25+2) = 5$
MASK: 255.255.255.1110 0000 (224)
NET: 23.27.24.0100 0000 (64)
FIRST: 23.27.24.0100 0001
LAST: 23.27.24.0101 1110
BCAST: 23.27.24.0101 1111

2023:DB8:27:A16::/64

#### VLAN 14 'Ingenieria'
40 equipos => $log_2(40+2) = 6$
MASK: 255.255.255.1100 0000 (192)
NET: 23.27.24.1100 0000 // bit shifted to the left (192)
FIRST: 23.27.24.1100 0001
LAST: 23.27.24.1111 1110
BCAST: 23.27.24.1111 1111

2023:DB8:27:A14::/64

## Edificio C

### VLAN20
MASK: 255.255.255.1000 0000 (128)
NET: 10.23.27.0000 0000 (0)
FIRST: 10.23.27.0000 0001
LAST: 10.23.27.0111 1110
BCAST: 10.23.27.0111 1111

### VLAN35
MASK: 255.255.255.0000 0000 (0)
NET: 23.27.26.0000 0000 (0)
FIRST: 23.27.26.0000 0001
LAST: 23.27.26.1111 1110
BCAST: 23.27.26.1111 11111

## Edificio D 

### VLAN40
40 equipos => $log_2(40+2) = 6$
MASK: 255.255.255.1100 0000 (192)
NET: 23.27.27.0000 0000
FIRST: 23.27.27.0000 0001
LAST: 23.27.27.0011 1110
BCAST: 23.27.27.0011 1111

2023:DB8:27:D40::/64

### VLAN10
10 equipos => $log_2(10+2) = 4$
MASK: 255.255.255.1111 0000 (240)
NET: 23.27.27.0100 0000 (64)
FIRST: 23.27.27.0100 0001
LAST: 23.27.27.0100 1110
BCAST: 23.27.27.0100 1111

2023:DB8:27:D10::/64

## Routers
### SWITCH-R1
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0101 0000 (80)
FIRST: 23.27.27.0101 0001 (R1)
LAST: 23.27.27.0101 0010 (SW)
BCAST: 23.27.27.0101 0011

2023:DB8:27:X0::/64

### R1-R3
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0101 0100 (84)
FIRST: 23.27.27.0101 0101 (R1)
LAST: 23.27.27.0101 0110 (R3)
BCAST: 23.27.27.0101 0111

2023:DB8:27:X1::/64

### R3-R4
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0101 1000 (88)
FIRST: 23.27.27.0101 1001 (R3)
LAST: 23.27.27.0101 1010 (R4)
BCAST: 23.27.27.0101 1011

2023:DB8:27:X2::/64

### R4-R6
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0101 1100 (92)
FIRST: 23.27.27.0101 1011 (R4)
LAST: 23.27.27.0101 1010 (R6)
BCAST: 23.27.27.0101 1011

2023:DB8:27:X3::/64

### R3-R5
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0101 1100 (96)
FIRST: 23.27.27.0101 1101 (R3)
LAST: 23.27.27.0101 1110 (R5)
BCAST: 23.27.27.0101 1111

2023:DB8:27:X4::/64


