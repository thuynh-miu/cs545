const PostDetails = (props) => {
    return (
        <div style={styles.postDetails}>
            <div style={styles.postDetailsTitle}>
                <p style={{textDecoration: 'underline'}}>{props.post.title}</p>
                <p>{props.post.author}</p>
            </div>
            <div style={styles.postDetailsContent}>
                <p>This is the content in the post…</p>
            </div>
            <div style={styles.postDetailsButton}>
                <button style={styles.buttonUnderline} type="submit">{'Edit'}</button>
                <button style={styles.buttonUnderline} type="submit">{'Delete'}</button>
            </div>
        </div>
    )
}

const styles = {
    postDetails: {
        marginTop: '20px',
        width: '600px',
        border: '2px solid #385d8a',
        padding: '10px',
    },
    postDetailsTitle: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    postDetailsContent: {
        display: 'flex',
        flexDirection: 'column'
    },
    postDetailsButton: {
        display: 'flex',
        justifyContent: 'center',
        marginTop: '40px'
    },
    buttonUnderline: {
        border: 'none',
        backgroundColor: 'transparent',
        textDecoration: 'underline',
        marginRight: '10px',
        fontSize: '16px',
        color: 'red',
        cursor: 'pointer',
        margin: '5px 20px'
    }
}

export default PostDetails