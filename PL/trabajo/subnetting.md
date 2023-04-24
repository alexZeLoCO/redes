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

# ACLs

## Edificio A

access-list 110 permit tcp any 23.27.24.2 0.0.0.0 eq www
access-list 110 permit tcp any 23.27.24.3 0.0.0.0 eq smtp
access-list 110 permit ip 23.27.27.0 0.0.0.63 23.27.24.0 0.0.0.15
access-list 110 deny ip any 23.27.24.0 0.0.0.15
access-list 110 permit ip any any

## Edificio B

access-list 110 permit tcp 10.23.27.0 0.0.0.255 23.27.24.3 0.0.0.0 eq www
access-list 110 permit tcp 10.23.27.0 0.0.0.255 23.27.25.67 0.0.0.0 eq www
access-list 110 permit tcp 10.23.27.0 0.0.0.255 23.27.24.2 0.0.0.0 eq smtp
access-list 110 permit tcp 10.23.27.0 0.0.0.255 23.27.25.66 0.0.0.0 eq smtp
access-list 110 permit tcp 10.23.27.0 0.0.0.255 23.27.27.66 0.0.0.0 eq smtp


access-list 120 deny ip 156.35.0.0 0.0.255.255 23.27.25.68 0.0.0.0
access-list 120 deny ip 156.35.0.0 0.0.255.255 23.27.25.69 0.0.0.0

access-list 120 permit ip 156.23.27.0 0.0.0.255 23.27.25.64 0.0.0.63

## Edificio C D

end
config term

no access-list 110
no access-list 120
no access-list 130

access-list 110 permit ip any 23.27.24.3 0.0.0.0 eq www
access-list 110 permit ip any 23.27.25.67 0.0.0.0 eq www
access-list 110 permit ip any 23.27.24.2 0.0.0.0 eq smtp
access-list 110 permit ip any 23.27.25.66 0.0.0.0 eq smtp
access-list 110 permit ip any 23.27.27.66 0.0.0.0 eq smtp
access-list 110 permit ip any 156.35.0.0 0.0.255.255

access-list 120 permit ip any 23.27.27.64 0.0.0.15
access-list 120 permit ip any 23.27.24.3 0.0.0.0
access-list 120 permit ip any 23.27.25.67 0.0.0.0
access-list 120 permit ip any 23.27.24.2 0.0.0.0
access-list 120 permit ip any 23.27.25.66 0.0.0.0

access-list 130 permit ip 23.27.26.0 0.0.0.255 any
access-list 130 permit ip 23.27.24.16 0.0.0.15 any
access-list 130 permit ip 23.27.24.192 0.0.0.63 any
access-list 130 permit ip 156.23.27.0 0.0.0.255 any

int g0/1.10 
ip access-group 110 out
int g0/2.20 
ip access-group 110 out
int g0/1.35 
ip access-group 120 out
int g0/1.40 
ip access-group 120 out
int g0/1.10 
ip access-group 130 out

end

sh run

