/*
 * Prefix Calculator
 * Module1Activity4A.js
 * 
 * ndavispe
 * 5/30/25
 * SER421 Summer 25
 */

// TEST CASES
/* 
 * console.log(calc('{"operation": "add", "operand": 5}'));      // Returns: 5
 * console.log(calc('{"operation": "subtract", "operand": 2}')); // Returns: 3
 * console.log(calc('{"operation": "add", "operand": 19}'));     // Returns: 22
 */

let storedValue = 0; // init

function calc(jsonString) {
    try {
        // 1. parse JSON string
        const expression = JSON.parse(jsonString);
        
        // 2.1. extract operation & operand
        const { operation, operand } = expression;
        
        // 2.2. validate inputs
        if (typeof operation !== 'string') {
            throw new Error('Operation must be String');
        }
        if (typeof operand !== 'number') {
            throw new Error('Operand must be Number');
        }
        
        // 3. process the operation
        switch (operation.toLowerCase()) {
            case 'add' :
                storedValue += operand;
                break;
            case 'subtract' :
                storedValue -= operand;
                break;
            default :
                throw new Error(`${operation} Error`);
        }
        
        // 4. return
        return storedValue;
        
    } catch (error) {
        throw new Error(`${error.message}`);
    }
}