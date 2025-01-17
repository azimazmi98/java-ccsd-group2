import React, { useState, useEffect } from 'react';
import { useParams, useNavigate} from 'react-router-dom';
import './register-customer.css';
import GetData from '../data/getData';


const RegisterCustomer = () => {

    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [phoneNo, setPhoneNo] = useState('');
    const [cardNo, setCardNo] = useState('');
    const [cardCvv, setCardCvv] = useState('');
    const [cardExpiryDate, setCardExpiryDate] = useState('');

    const [nameError, setNameError] = useState('');
    const [emailError, setEmailError] = useState('');
    const [phoneError, setPhoneError] = useState('');
    const [cardError, setCardError] = useState('');
    const [cvvError, setCvvError] = useState('');
    const [expiryError, setExpiryError] = useState('');

    const API_BASE_URL = 'http://localhost:8082/api/customer';

    // Name validation
    const handleNameChange = (e) => {
        const input = e.target.value;
        setName(input);
    
        // Check if the input contains any numbers
        if (!input.trim()) {
            setNameError('Name is required');
        } else if (/\d/.test(input)) {
            // If input contains numbers, show an error
            setNameError('Name should not contain numbers');
        } else {
            setNameError('');
        }
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

    // Phone number validation
    const handlePhoneChange = (e) => {
        const input = e.target.value;
        setPhoneNo(input);

        // Phone number format validation (XXX-XXXXXXXX)
        if (!input.match(/^\d{3}-\d{8}$/)) {
            if (input.length > 0 && /[^\d-]/.test(input)) {
                setPhoneError('Phone number should only contain digits and hyphen.');
            } else {
                setPhoneError('Phone number should be in the format XXX-XXXXXXXX');
            }
        } else {
            setPhoneError('');
        }
    };

    // Card number validation
    const handleCardNumberChange = (e) => {
        const input = e.target.value;
        setCardNo(input);

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
        setCardCvv(input);

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
        setCardExpiryDate(input);

        // Expiry date format validation (MM/YYYY)
        if (!input.match(/^(0[1-9]|1[0-2])\/\d{4}$/)) {
            setExpiryError('Expiry date should be in the format MM/YYYY');
        } else {
            setExpiryError('');
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const customer = {
            name,
            email,
            phoneNo,
            cardNo,
            cardCvv,
            cardExpiryDate,
        };

        try {
            const response = await fetch(`${API_BASE_URL}/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(customer),
            });

            if (response.ok) {
                // Redirect to a success or login page after successful registration
                alert('Registration successful');
                navigate('/payment'); // Navigate to login page after registration
            } else {
                // Handle error response
                alert('Registration failed. Please try again.');
            }
        } catch (error) {
            alert('Error occurred: ' + error.message);
        }
    };

    return (
        <div className="register-customer-container">
            <h2>Registration</h2>
            <form onSubmit={handleSubmit}>
            <div className="form-group">
                <label>Name</label>
                <input 
                    type="text" 
                    placeholder="Full Name" 
                    value={name} 
                    onChange={handleNameChange} 
                    required 
                />
                {nameError && <span style={{ color: 'red' }}>{nameError}</span>}
            </div>

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
                <label>Phone Number</label>
                <input 
                    type="text" 
                    placeholder="eg: 012-34567890" 
                    value={phoneNo} 
                    onChange={handlePhoneChange} 
                    required 
                />
                {phoneError && <span style={{ color: 'red' }}>{phoneError}</span>}
            </div>

            <div className="form-group">
                <label>Card Number</label>
                <input 
                    type="text" 
                    placeholder="XXXX-XXXX-XXXX-XXXX" 
                    value={cardNo} 
                    onChange={handleCardNumberChange} 
                    required 
                />
                {cardError && <span style={{ color: 'red' }}>{cardError}</span>}
            </div>

            <div className="form-group">
                <label>CVV</label>
                <input 
                    type="text" 
                    placeholder="CVV" 
                    value={cardCvv} 
                    onChange={handleCvvChange} 
                    required 
                />
                {cvvError && <span style={{ color: 'red' }}>{cvvError}</span>}
            </div>

            <div className="form-group">
                <label>Expiry Date</label>
                <input 
                    type="text" 
                    placeholder="MM/YYYY" 
                    value={cardExpiryDate} 
                    onChange={handleExpiryDateChange} 
                    required 
                />
                {expiryError && <span style={{ color: 'red' }}>{expiryError}</span>}
            </div>

            <button type="submit">Register</button>
        </form>
        </div>
    );
};

export default RegisterCustomer;