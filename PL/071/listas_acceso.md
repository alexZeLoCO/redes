# Listas de acceso

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