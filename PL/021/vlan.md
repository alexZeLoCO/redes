- Dar de alta la VLAN: `Switch(config)# vlan \<NUM\>`
- Asignación de puertos a la VLAN:
	- `Switch(config)# interface \<TYPE\> \<NUM\>/\<MOD\>
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