import './Post.css';

const Post = ({ id, title, author, setSelected, deletePost }) => {
    return (
        <div className="container" onClick={setSelected} key={id}>
            <h3>Title: {title}</h3>
            <p>Author: {author}</p>
            <div className="Edit">
                <input
                    type="button"
                    value="Delete"
                    onClick={(e) => {
                        e.stopPropagation();
                        deletePost();
                    }} />
            </div>
        </div>
    );
}

export default Post;