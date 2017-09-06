.data

@ Memory Allocation and Initialization
.balign 4
printf_ind_format:
.asciz "Your entered %d\n"

.balign 4
scanf_format:
.asciz "%d"

.balign 4
num:
.word 0

.balign 4
instr1:
.asciz "Enter coin or select return.\n"

.balign 4
instr2:
.asciz "Make a selection or return: (C)Coke, (S)Sprite, (P) Dr.Pepper, (D) Diet Coke, or (M) Mellow yellow\n"

.balign 4
instr3:
.asciz "Cost of Coke, Sprite, Dr. Pepper, Diet Coke, Mellow Yellow is 55 cents.\n"

.balign 4
instr4:
.asciz "Welcome to Mr. Zippy's soft drink vending machine.\n"

.balign 4
print_total:
.asciz "Total is %d cents\n"

.balign 4
print_return:
.asciz "Return is %d cents\n"

.balign 4
scanf_str_format:
.asciz "%s"

.balign 4
coke_instr:
.asciz "Selection is Coke.\n"

.balign 4
sprite_instr:
.asciz "Selection is Sprite.\n"

.balign 4
pepper_instr:
.asciz "Selection is Dr.Pepper.\n"

.balign 4
dietcoke_instr:
.asciz "Selection is Diet Coke.\n"

.balign 4
mellow_yellow_instr:
.asciz "Selection is Mellow Yellow.\n"

.balign 4
str: .word 0

.text

.global main

main:

@push {ip, lr} @Used with pop at end of main, for ease in returning

    ldr r0, =instr4                             /*Printing the welcome message */
    bl printf                                       
    
    ldr r0, =instr3                             /*Printing the cost of soft drinks */
    bl printf

@read input character from user
    ldr r7,=num                                 
    ldr r8,[r7]

loop:
    
    cmp r8, #55                                 /*Checking if the total is 55 cents or  more*/
    bge selection
    
    ldr r0, =instr1
    bl printf
    
    ldr r1, =str                                /*Getting a character input from the user for coin*/
    ldr r0, = scanf_str_format
    bl scanf

    ldr r5, =str
    ldr r5, [r5]

    cmp r5, #81                                 /*Checking if the entered coin is Quater*/
    beq quater
    
    cmp r5, #80                                 /*Checking if the entered coin is Penny*/
    beq penny
    
    cmp r5, #68                                 /*Checking if the entered coin is Dime*/
    beq dime
    
    cmp r5, #70                                 /*Checking if the entered coin is Fifty cents*/
    beq fifty
    
    cmp r5, #66                                 /*Checking if the entered bill is Dollar*/
    beq dollar
    
    cmp r5, #78                                 /*Checking if the entered coin is Nickel*/
    beq nickel
    
    cmp r5, #82                                 /*Checking if the entered choice is return*/
    beq return_change 
    
quater:                                         /*Adding 25 cents to the total*/
    mov r6, #25
    add r8,r8,r6
    mov r1,r8
    ldr r0, =print_total 
    bl printf
    b loop
    

nickel:                                         /*Adding 5 cents to the total*/
    mov r6, #5
    add r8,r8,r6
    mov r1,r8
    ldr r0, =print_total 
    bl printf
    b loop

penny:                                          /*Adding 1 cent to the total*/
    mov r6, #1
    add r8,r8,r6
    mov r1,r8
    ldr r0, =print_total 
    bl printf

    b loop
    

dime:                                           /*Adding 10 cents to the total*/
    mov r6, #10
    add r8,r8,r6
    mov r1,r8
    ldr r0, =print_total 
    bl printf
    b loop

    

fifty:                                          /*Adding 50 cents to the total*/
    mov r6, #50
    add r8,r8,r6
    mov r1,r8
    ldr r0, =print_total 
    bl printf
    b loop

dollar:                                         /*Adding 100 cents to the total*/
    mov r6, #100
    add r8,r8,r6
    mov r1,r8
    ldr r0, =print_total 
    bl printf
    b loop
    
return_change:                                  /*Returning change*/
    mov r1,r8 
    ldr r0, =print_return
    bl printf
    b end
    
selection:                                      /*prompting the user to make a selection or return*/
    ldr r0, =instr2
    bl printf
    ldr r1, =str
    ldr r0, = scanf_str_format
    bl scanf
    
    
    ldr r5, =str
    ldr r5, [r5]

    cmp r5, #67                                 /*Checking if the choice is Coke*/
    beq coke
    
    cmp r5, #83                                 /*Checking if the choice is Sprite*/
    beq sprite
    
    cmp r5, #80                                 /*Checking if the choice is Pepper*/
    beq pepper
    
    cmp r5, #68                                 /*Checking if the choice is Diet Coke*/
    beq dietcoke
    
    cmp r5, #77                                 /*Checking if the choice is Mellow Yellow*/
    beq mellow_yellow
    
    cmp r5, #82                                 /*returning change*/
    beq return_change 
    
coke:                                           /*Selection is Coke and return change*/
    ldr r0, =coke_instr
    bl printf
    sub r8,r8,#55
    ldr r0, =print_return 
    mov r1,r8
    bl printf
    b end

    
sprite:                                         /*Selection is Sprite and return change*/
    ldr r0, =sprite_instr
    bl printf
    sub r8,r8,#55
    ldr r0, =print_return 
    mov r1,r8
    bl printf
    b end

pepper:                                         /*Selection is Dr. Pepper and return change*/
    ldr r0, =pepper_instr
    bl printf
    sub r8,r8,#55
    ldr r0, =print_return 
    mov r1,r8
    bl printf
    b end

dietcoke:                                       /*Selection is Diet Coke and return change*/
    ldr r0, =dietcoke_instr
    bl printf
    sub r8,r8,#55
    ldr r0, =print_return 
    mov r1,r8
    bl printf
    b end

mellow_yellow:                                  /*Selection is Mellow Yellow and return change*/
    ldr r0, =mellow_yellow_instr
    bl printf
    sub r8,r8,#55
    ldr r0, =print_return 
    mov r1,r8
    bl printf
    b end


end:
@pop {ip, pc} @Used with push at start of Main, allowing program to end.
