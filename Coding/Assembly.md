# Using RISC V

## Start of Program
A typical RISC V or Assembly program starts with the following lines:
```assembly
    ORG     96              ; declares init address
    DD      42, 100, 19     ; stores values in mem
    addi    x1, x0, 96      ; initializes init address
```
The first line contains the `ORG` command, which declares the initial address of the program. The `DD` command stores its arguments in memory to be later used by the program. The `i` th value is stored in address `ORG + 8(i-1)`. Lastly, the `addi` command with `x1, x0` arguments initializes the registers to the initial address in `ORG`.

Values need not be explicitly stored in memory as will be discussed in the following section.

## Registers
There are two primary methods of loading values into registers:
```assembly
    addi    x4, x0, 42      ; method 1
    ld      x4, 8(x1)       ; method 2
```
In the first method, the value 42 is directly loaded into `x4`. Incidentally, in the second methods the value must first be stored in memory via the `DD` command. As aforementioned, to load the `i`th number of `DD`, then `8n(x1)` is used.

Consequently, values are loaded from registers into memory via
```assembly
    sd      x19, 208(x1)    ; stores reg into mem
```
which stores the value contained in `x19` into memory address `208 + ORG`.

## Conditionals
In RISC V, conditional statements such as `if else` work unlike other languages, and operate using the following syntax:
```assembly
Main:   bne     x1, x2, Else    ; if x1 != x2 goto Else
        ; statements if true
        beq     x0, x0, Exit    ; goto Exit

Else:   ; statements if false
Exit:
```
This code snippet is equivalent to the conditional `if (x1 == x2) {} else {}`. Evidently, the `beq` command goes to `Else` if `x1 == x2`, with `bne` functioning the opposite way.
