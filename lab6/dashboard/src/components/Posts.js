import Post from './Post'

const Posts = (props) => {

    const postsList = props.posts.map(post => {
        return (
            <Post
                id={post.id}
                title={post.title}
                author={post.author}
                onClick={() => props.setSelected(post.id)}
                key={post.id}
            />
        )
    })

    return (
        <div style={styles.container}>
            {postsList}
        </div>
    )
}

const styles = {
    container: {
        display: 'flex',
        justifyContent: 'center',
        width: '600px'
    }
}

export default Posts;