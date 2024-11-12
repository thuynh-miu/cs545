import PostItem from './Post'

const Posts = ({posts, onClick}) => {

    const postsList = posts.map(post => {
        return (
            <PostItem
                id={post.id}
                title={post.title}
                author={post.author}
                onClick={() => onClick(post.id)}
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