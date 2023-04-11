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
