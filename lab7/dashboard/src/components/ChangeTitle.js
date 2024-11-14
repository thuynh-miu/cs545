import {useEffect, useState} from "react";

const ChangeTitle = ({ title, onChangeTitle }) => {
    const [localTitle, setLocalTitle] = useState(title);

    const handleInputChange = (e) => {
        setLocalTitle(e.target.value);
    };

    useEffect(() => {
        setLocalTitle(title); // Reset localTitle when title prop changes
    }, [title]);

    const handleConfirm = () => {
        onChangeTitle(localTitle);  // Update parent state when confirmed
    };

    return (
        <div style={styles.form}>
            <label style={styles.label}>Update Title: </label>
            <input
                style={styles.input}
                type="text"
                placeholder="title"
                value={localTitle}
                onChange={handleInputChange}
            />
            <button style={styles.button} onClick={handleConfirm}>{'Change Title'}</button>
        </div>
    )
}

const styles = {
    form: {
        marginTop: '20px',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'start',
        justifyContent: 'center',
        padding: '8px 25px',
        backgroundColor: '#f5f5f5',
        boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
        width: '600px'
    },
    label: {
        fontSize: '18px',
        fontWeight: 'bold',
        color: '#333',
        marginRight: '10px',
    },
    input: {
        marginTop: '10px',
        padding: '5px',
        width: '40%',
        height: '30px',
        borderRadius: '5px',
        border: '1px solid #ccc',
        fontSize: '16px'
    },
    button: {
        width: '160px',
        height: '40px',
        padding: '5px 10px',
        borderRadius: '5px',
        backgroundColor: '#4CAF50',
        color: '#fff',
        border: 'none',
        fontSize: '16px',
        cursor: 'pointer',
        transition: 'background-color 0.3s ease',
        marginTop: '10px'
    }
}

export default ChangeTitle;