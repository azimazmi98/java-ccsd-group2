import React, { useState, useEffect } from 'react';
import { useParams, useNavigate} from 'react-router-dom';
import './payment.css';


const Payment = () => {
    const [email, setEmail] = useState("");
    const [amount, setAmount] = useState('');
    const [cardNumber, setCardNumber] = useState('');
    const [expiryDate, setExpiryDate] = useState('');
    const [cvv, setCvv] = useState('');
    const navigate = useNavigate();

    const [emailError, setEmailError] = useState('');
    const [expiryError, setExpiryError] = useState('');
    const [cardError, setCardError] = useState('');
    const [amountError, setAmountError] = useState('');
    const [cvvError, setCvvError] = useState('');

    const API_BASE_URL = 'http://localhost:8082/api/payment';

    const handleRegistration = () => {
        navigate('/register'); // Redirect to the registration page
    };

    // Email validation
    const handleEmailChange = (e) => {
        const input = e.target.value;
        setEmail(input);

        if (!input.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{3,}$/)) {
            setEmailError('Please enter a valid email address');
        } else {
            setEmailError('');
        }
    };

    // Card number validation
    const handleCardNumberChange = (e) => {
        const input = e.target.value;
        setCardNumber(input);

        // Card number format validation (XXXX-XXXX-XXXX-XXXX)
        if (!input.match(/^\d{4}-\d{4}-\d{4}-\d{4}$/)) {
            setCardError('Card number should be in the format XXXX-XXXX-XXXX-XXXX');
        } else {
            setCardError('');
        }
    };

    // CVV validation
    const handleCvvChange = (e) => {
        const input = e.target.value;
        setCvv(input);

        // CVV validation (3 digits)
        if (!input.match(/^\d{3}$/)) {
            setCvvError('CVV should be 3 digits');
        } else {
            setCvvError('');
        }
    };

    // Expiry date validation
    const handleExpiryDateChange = (e) => {
        const input = e.target.value;
        setExpiryDate(input);

        // Expiry date format validation (MM/YYYY)
        if (!input.match(/^(0[1-9]|1[0-2])\/\d{4}$/)) {
            setExpiryError('Expiry date should be in the format MM/YYYY');
        } else {
            setExpiryError('');
        }
    };

    const handleAmountChange = (e) => {
        const input = e.target.value;
        setAmount(input);

        // Validate amount (between 0.00 and 999999.00)
        const amountPattern = /^(?!0(\.00?)?$)([1-9][0-9]{0,5}(\.[0-9]{2})?|0(\.[0-9]{1,2})?)$/;

        if (!amountPattern.test(input) || parseFloat(input) < 0.00 || parseFloat(input) > 999999.00) {
            setAmountError('Amount must be between 0.00 and 999999.00');
            return;
        } else {
            setAmountError(''); // Clear error if valid
        }
    }
    
    const isFormValid = !emailError && !expiryError && !cardError && !amountError && !cvvError;

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Prepare payment data to be sent
        const paymentData = {
            email,
            amount: parseFloat(amount), // Ensure the amount is sent as a number
            cardNumber,
            expiryDate,
            cvv: parseInt(cvv, 10), // Ensure the CVV is sent as an integer
        };

        try {
            const response = await fetch(`${API_BASE_URL}/submit`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(paymentData), // Convert the data to JSON
            });

            if (response.ok) {
                const result = await response.json();
                console.log('Payment submitted successfully', result);
                alert('PAYMENT SUCCESSFUL.\nThank you for shopping with us.')
                navigate("/"); 
            } else {
                const errorResult = await response.text();
                alert("TRANSACTION FAILED\nPlease check your details and regiser if you are new.")
                console.error('Error submitting payment:', errorResult);
            }
        } catch (error) {
            console.error('Error during payment submission:', error);
            alert("INTERNAL SERVER ERROR\nPlease contact our support.")
        }
    };


    return (        
        <div className="payment-container">
            <h2>Payment</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Email</label>
                    <input 
                        type="email" 
                        placeholder="john@example.com" 
                        value={email} 
                        onChange={handleEmailChange} 
                        required 
                    />
                    {emailError && <span style={{ color: 'red' }}>{emailError}</span>}
                </div>
                <div className="form-group">
                    <label>Amount</label>
                    <input 
                        type="text" 
                        placeholder="0.00" 
                        value={amount} 
                        onChange={handleAmountChange} 
                        required 
                        pattern="^(?!0(\.00?)?$)([1-9][0-9]{0,5}(\.[0-9]{2})?|0(\.[0-9]{1,2})?)$" // Regex for amount format
                        min="0.00"
                        max="999999.00"
                        step="0.01" // Allow up to two decimal places
                    />
                    {amountError && <span style={{ color: 'red' }}>{amountError}</span>} {/* Show error message */}
                </div>
                <div className="form-group">
                    <label>Card Number</label>
                    <input 
                        type="text" 
                        placeholder="XXXX-XXXX-XXXX" 
                        value={cardNumber} 
                        onChange={handleCardNumberChange} 
                        required 
                    />
                    {cardError && <span style={{ color: 'red' }}>{cardError}</span>} {/* Show error message */}
                </div>
                <div className="form-group">
                    <label>Expiry Date</label>
                    <input 
                        type="text" 
                        placeholder="Expiry Date (MM/YYYY)" 
                        value={expiryDate} 
                        onChange={handleExpiryDateChange} 
                        required 
                        pattern="^(0[1-9]|1[0-2])\/\d{4}$" // Regex pattern for MM/YYYY format
                    />
                    {expiryError && <span style={{ color: 'red' }}>{expiryError}</span>} {/* Show error message */}
                </div>
                <div className="form-group">
                    <label>CVV</label>
                    <input 
                        type="text" 
                        placeholder="CVV" 
                        value={cvv} 
                        onChange={handleCvvChange} 
                        required 
                        pattern="^\d{3}$" // Regex pattern for 3-digit CVV
                    />
                    {cvvError && <span style={{ color: 'red' }}>{cvvError}</span>} {/* Show error message */}
                </div>
                <button type="submit" disabled={!isFormValid}>Submit Payment</button>
                <button 
                    type="button" 
                    onClick={handleRegistration} 
                    style={{ marginTop: '10px' }}
                >
                    Register
                </button>
            </form>
        </div>
    );
};

export default Payment;