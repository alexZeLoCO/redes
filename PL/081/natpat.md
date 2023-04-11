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
