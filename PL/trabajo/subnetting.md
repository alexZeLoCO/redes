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

2023:DB8:27:F0::/64

### R1-R3
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0101 0100 (84)
FIRST: 23.27.27.0101 0101 (R1)
LAST: 23.27.27.0101 0110 (R3)
BCAST: 23.27.27.0101 0111

2023:DB8:27:F1::/64

### R3-R4
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0101 1000 (88)
FIRST: 23.27.27.0101 1001 (R3)
LAST: 23.27.27.0101 1010 (R4)
BCAST: 23.27.27.0101 1011

2023:DB8:27:F2::/64

### R4-R6
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0101 1100 (92)
FIRST: 23.27.27.0101 1011 (R4)
LAST: 23.27.27.0101 1010 (R6)
BCAST: 23.27.27.0101 1011

2023:DB8:27:F3::/64

### R3-R5
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0101 1100 (96)
FIRST: 23.27.27.0101 1101 (R3)
LAST: 23.27.27.0101 1110 (R5)
BCAST: 23.27.27.0101 1111

2023:DB8:27:F4::/64

### R1-R2
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0110 0000 (100)
FIRST: 23.27.27.0110 0001 (R1)
LAST: 23.27.27.0110 0010 (R2)
BCAST: 23.27.27.0110 0011

2023:DB8:27:F5::/64

### R2-R4
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0110 0100 (104)
FIRST: 23.27.27.0110 0101 (R2)
LAST: 23.27.27.0110 0110 (R4)
BCAST: 23.27.27.0110 0111

2023:DB8:27:F6::/64

### R1-R7
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0110 1000 (108)
FIRST: 23.27.27.0110 1001 (R1)
LAST: 23.27.27.0110 1010 (R7)
BCAST: 23.27.27.0110 1011

2023:DB8:27:F7::/64

### R2-R7
2 equipos => $log_2(2+2) = 2$
MASK: 255.255.255.1111 1100 (252)
NET: 23.27.27.0110 1000 (112)
FIRST: 23.27.27.0110 1001 (R2)
LAST: 23.27.27.0110 1010 (R7)
BCAST: 23.27.27.0110 1011

2023:DB8:27:F8::/64

## Oficina remota
MASK: 255.255.255.0
NET: 156.23.26.0

2023:DB8:27:F10::/64

# ACLs

## Internet
MASK: 255.255.0.0
NET: 156.35.0.0

2023:CAFE::/48

## Edificio A

end
config term

no access-list 110

! Permit DHCP
access-list 110 permit udp any eq bootpc any eq bootps

access-list 110 permit tcp any 23.27.24.2 0.0.0.0 eq www
access-list 110 permit tcp any 23.27.24.3 0.0.0.0 eq smtp
access-list 110 permit ip 23.27.27.0 0.0.0.63 23.27.24.0 0.0.0.15
access-list 110 deny ip any 23.27.24.0 0.0.0.15
access-list 110 permit ip any any

int g1/1/1
no ip access-group 110 out
ip access-group 110 in

end

## Edificio B

end
config term

! Cleanup
no access-list 110
no access-list 120

! Permit DHCP
access-list 110 permit udp any eq bootpc any eq bootps
! Permit any to webA
access-list 110 permit tcp 10.23.27.0 0.0.0.255 23.27.24.3 0.0.0.0 eq www
! Permit any to webB
access-list 110 permit tcp 10.23.27.0 0.0.0.255 23.27.25.67 0.0.0.0 eq www
! Permit any to mailA
access-list 110 permit tcp 10.23.27.0 0.0.0.255 23.27.24.2 0.0.0.0 eq smtp
! Permit any to mailB
access-list 110 permit tcp 10.23.27.0 0.0.0.255 23.27.25.66 0.0.0.0 eq smtp
! Permit any to mailCD
access-list 110 permit tcp 10.23.27.0 0.0.0.255 23.27.27.66 0.0.0.0 eq smtp

! Permit DHCP
access-list 120 permit udp any eq bootpc any eq bootps
acc 120 permit tcp 23.27.24.0 0.0.3.255 host 23.27.25.66 eq smtp
acc 120 permit tcp 23.27.24.0 0.0.3.255 host 23.27.25.67 eq www
acc 120 permit tcp 23.27.24.0 0.0.3.255 host 23.27.25.68
acc 120 permit tcp 23.27.24.0 0.0.3.255 host 23.27.25.69

! Permit remote to servsB
access-list 120 permit ip 156.23.27.0 0.0.0.255 23.27.25.64 0.0.0.63

int g0/1.35
no ip access-group 120 in
ip access-group 120 out
int g0/1.10
no ip access-group 110 out
ip access-group 110 in

end

## Edificio C D

end
config term

no access-list 110
no access-list 120
no access-list 130

! Permit DHCP
access-list 110 permit udp any eq bootpc any eq bootps
! Permit any (invitados) to servs
access-list 110 permit tcp any 23.27.24.3 0.0.0.0 eq www
access-list 110 permit tcp any 23.27.25.67 0.0.0.0 eq www
access-list 110 permit tcp any 23.27.24.2 0.0.0.0 eq smtp
access-list 110 permit tcp any 23.27.25.66 0.0.0.0 eq smtp
access-list 110 permit tcp any 23.27.27.66 0.0.0.0 eq smtp
! Permit any (invitados) to internet
access-list 110 permit ip any 156.35.0.0 0.0.255.255

! Permit DHCP
access-list 120 permit udp any eq bootpc any eq bootps
! Permit any (personal) to servs
access-list 120 permit ip any 23.27.27.64 0.0.0.15
access-list 120 permit ip any 23.27.24.3 0.0.0.0
access-list 120 permit ip any 23.27.25.67 0.0.0.0
access-list 120 permit ip any 23.27.24.2 0.0.0.0
access-list 120 permit ip any 23.27.25.66 0.0.0.0

! Permit DHCP
access-list 130 permit udp any eq bootpc any eq bootps
! Permit C to servsCD
access-list 130 permit ip 23.27.26.0 0.0.0.255 23.27.27.67 0.0.0.0
! Permit D to servsCD
access-list 130 permit ip 23.27.27.0 0.0.0.255 23.27.27.67 0.0.0.0
! Permit RRHH to servsCD
access-list 130 permit ip 23.27.24.16 0.0.0.15 23.27.27.67 0.0.0.0
! Permit Ingenieria to servsCD
access-list 130 permit ip 23.27.24.192 0.0.0.63 23.27.27.67 0.0.0.0
! Permit remote to servsCD
access-list 130 permit ip 156.23.27.0 0.0.0.255 23.27.27.67 0.0.0.0
! Permit C to servsCD
access-list 130 permit ip 23.27.26.0 0.0.0.255 23.27.27.68 0.0.0.0
! Permit D to servsCD
access-list 130 permit ip 23.27.27.0 0.0.0.255 23.27.27.68 0.0.0.0
! Permit RRHH to servsCD
access-list 130 permit ip 23.27.24.16 0.0.0.15 23.27.27.68 0.0.0.0
! Permit Ingenieria to servsCD
access-list 130 permit ip 23.27.24.192 0.0.0.63 23.27.27.68 0.0.0.0
! Permit remote to servsCD
access-list 130 permit ip 156.23.27.0 0.0.0.255 23.27.27.68 0.0.0.0
! Permit any to servsCD
access-list 130 permit ip any 23.27.27.66 0.0.0.0

int g0/1.20
no ip access-group 110 out
ip access-group 110 in
int g0/2.10
no ip access-group 110 out
ip access-group 110 in
int g0/1.35
no ip access-group 120 out
ip access-group 120 in
int g0/1.40
no ip access-group 120 out
ip access-group 120 in
int g0/1.10
no ip access-group 130 in
ip access-group 130 out

end

sh run
