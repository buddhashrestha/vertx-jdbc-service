/* -- printf01.s */
.data

/* First message */
.balign 4
message1: .asciz "Hey, type a number: "

/* Second message */
.balign 4
message2: .asciz "I read the number %c\n"

/* third message */
.balign 4
message3: .asciz "Your change is :  %d\n"

/* Format pattern for scanf */
.balign 4
scan_pattern : .asciz "%c"

/* Format pattern for scanf */
.balign 4
return_change_pattern : .asciz "%d"
                        .asciz "\nThank you."

/* Where scanf will store the number read */
.balign 4
number_read: .word 0

.balign 4
init_value: .word 0

.balign 4
return: .word 0


.text

.global main
main:
    ldr r1, address_of_return        /* r1 ? &address_of_return */
    str lr, [r1]                     /* *r1 ? lr */

    ldr r0, address_of_message1      /* r0 ? &message1 */
    bl printf                        /* call to printf */
    mov r9,#0
    b enterAndCompare


quater:
    
    add r9,r9, #25
    cmp r9, #55
    bl enterAndCompare
    b process

process:
    cmp r9, #55
    bne returnChange
    ldr r0, address_of_message3      /* r0 ? &message2 */
    mov r1, #0   
    bl printf
    b end

returnChange:
    sub r9, r9,#55
    str r9, [sp]

    ldr r0, address_of_message3      /* r0 ? &message2 */
    ldr r1, [sp]   
    bl printf
    b end



enterAndCompare:
    ldr r1, =number_read
    ldr r0, =scan_pattern
    bl scanf
    
    ldr r6, =number_read
    ldr r6, [r6]
    
    cmp r6, #81
    beq quater

    b end


end:
    ldr lr, address_of_return        /* lr ? &address_of_return */
    ldr lr, [lr]                     /* lr ? *lr */
    bx lr                            /* return from main using lr */
address_of_message1 : .word message1
address_of_message2 : .word message2
address_of_message3 : .word message3
address_of_scan_pattern : .word scan_pattern
address_of_number_read : .word number_read
address_of_return : .word return

/* External */
.global printf
.global scanf