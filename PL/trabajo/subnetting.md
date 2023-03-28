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
NET: 23.27.24.0000 0000
FIRST: 23.27.24.0000 0001
LAST: 23.27.24.0000 1110
BCAST: 23.27.24.0000 1111

#### VLAN 15 'RRHH'
8 equipos => $log_2(8+2) = 4$
MASK: 255.255.255.1111 0000 (240)
NET: 23.27.24.0001 0000
FIRST: 23.27.24.0001 0001
LAST: 23.27.24.0001 1110
BCAST: 23.27.24.0001 1111

#### VLAN 13 'Direccion'
20 equipos => $log_2(20+2) = 5$
MASK: 255.255.255.1110 0000 (224)
NET: 23.27.24.0010 0000
FIRST: 23.27.24.0010 0001
LAST: 23.27.24.0011 1110
BCAST: 23.27.24.0011 1111

#### VLAN 16 'Contabilidad'
25 equipos => $log_2(25+2) = 5$
MASK: 255.255.255.1110 0000 (224)
NET: 23.27.24.0100 0000
FIRST: 23.27.24.0100 0001
LAST: 23.27.24.0101 1110
BCAST: 23.27.24.0101 1111

#### VLAN 14 'Ingenieria'
40 equipos => $log_2(40+2) = 6$
MASK: 255.255.255.1100 0000 (192)
NET: 23.27.24.1100 0000 // bit shifted to the left
FIRST: 23.27.24.1100 0001
LAST: 23.27.24.1111 1110
BCAST: 23.27.24.1111 1111

