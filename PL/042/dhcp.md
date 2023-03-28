# DCHP
Configuración utilizando el router.
| Orden | Emsior | MSG | Significado |
| 1. | Cliente | `DHCPDISCOVER` | "Deseo una dirección" |
| 2. | Servidor | `DHCPOFFER` | "Ofrezco esta dirección" |
| 3. | Cliente | `DHCPREQUEST` | "Acepto esta dirección" |
| 4. | Servidor | `DHCPACK` | "Confirmo la aceptación" |

## DHCPIPv4
### Setup
```cisco
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
```cisco
R1# show running-config | section dhcp
R1# show ip dhcp binding
R1# show ip dhcp server statistics
R1# show ip dhcp conflict
```

```cisco
PC1> ipconfig /all
PC1> ipconfig /release
PC1> ipconfig /renew
```