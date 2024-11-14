const Post = ({id, title, author, onClick}) => {
    return (
        <div onClick={onClick} style={styles.container} key={id}>
            <p>Id: {id}</p>
            <p>Title: {title}</p>
            <p>Author: {author}</p>
        </div>
    );
}

const styles = {
    container: {
        border: '2px solid #385d8a',
        background: '#4f81bd',
        margin: '10px',
        padding: '10px',
        color: '#fff',
        fontSize: '20px',
        flex: 1,
        maxWidth: '160px',
        cursor: 'pointer'
    }
}

export default Post;